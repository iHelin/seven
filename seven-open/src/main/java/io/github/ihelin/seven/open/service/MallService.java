package io.github.ihelin.seven.open.service;

import io.github.ihelin.seven.common.dto.SkuEsModel;
import io.github.ihelin.seven.common.utils.JsonUtils;
import io.github.ihelin.seven.open.config.ESConfig;
import io.github.ihelin.seven.open.vo.SearchParam;
import io.github.ihelin.seven.open.vo.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author iHelin
 * @since 2021/1/18 11:26
 */
@Service
public class MallService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public SearchResult search(SearchParam searchParam) {
        SearchRequest searchRequest = buildSearchRequest(searchParam);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, ESConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            logger.error("search error", e);
        }
        SearchResult result = buildSearchResult(searchResponse, searchParam);
        return result;
    }

    private SearchRequest buildSearchRequest(SearchParam searchParam) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //构建DSL语句 查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(searchParam.getKeyword())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuTitle", searchParam.getKeyword()));
        }
        if (searchParam.getCatalog3Id() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalogId", searchParam.getCatalog3Id()));
        }
        if (searchParam.getBrandId() != null && searchParam.getBrandId().size() > 0) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brandId", searchParam.getBrandId()));
        }
        if (searchParam.getAttrs() != null && searchParam.getAttrs().size() > 0) {
            for (String attr : searchParam.getAttrs()) {
                BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
                String[] s = attr.split("_");
                String attrId = s[0];
                String[] attrValues = s[1].split(":");
                queryBuilder.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                queryBuilder.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("attrs", queryBuilder, ScoreMode.None);
                boolQueryBuilder.filter(nestedQueryBuilder);
            }

        }

        if (searchParam.getHasStock() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", searchParam.getHasStock() == 1));
        }

        if (StringUtils.isNotEmpty(searchParam.getSkuPrice())) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] prices = searchParam.getSkuPrice().split("_");
            if (prices.length == 2) {
                rangeQuery.gte(prices[0]).lte(prices[1]);
            } else if (prices.length == 1) {
                if (searchParam.getSkuPrice().startsWith("_")) {
                    rangeQuery.lte(prices[0]);
                } else if (searchParam.getSkuPrice().endsWith("_")) {
                    rangeQuery.gte(prices[0]);
                }
            }
            boolQueryBuilder.filter(rangeQuery);
        }
        sourceBuilder.query(boolQueryBuilder);

        //排序
        if (StringUtils.isNotEmpty(searchParam.getSort())) {
            String sort = searchParam.getSort();
            String[] s = sort.split("_");
            SortOrder sortOrder = "ase".equalsIgnoreCase(s[1]) ? SortOrder.ASC : SortOrder.DESC;
            sourceBuilder.sort(s[0], sortOrder);
        }
        //分页
        int pageSize = 2;
        sourceBuilder.from((searchParam.getPageNum() - 1) * pageSize);
        sourceBuilder.size(pageSize);
        //高亮
        if (StringUtils.isNotEmpty(searchParam.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style='color:red;'>");
            highlightBuilder.postTags("</b>");
            sourceBuilder.highlighter(highlightBuilder);
        }
        //聚合分析
        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brand_agg");
        brandAgg.field("brandId").size(50);
        brandAgg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brandAgg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        sourceBuilder.aggregation(brandAgg);

        TermsAggregationBuilder catalogAgg = AggregationBuilders.terms("catalog_agg");
        catalogAgg.field("catalogId").size(20);
        catalogAgg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        sourceBuilder.aggregation(catalogAgg);

        NestedAggregationBuilder nestedAggregationBuilder = AggregationBuilders.nested("attr_agg", "attrs");
        TermsAggregationBuilder attrsIdAgg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        attrsIdAgg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        attrsIdAgg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        nestedAggregationBuilder.subAggregation(attrsIdAgg);
        sourceBuilder.aggregation(nestedAggregationBuilder);

        logger.debug("\n构建语句：->\n" + sourceBuilder.toString());
        SearchRequest searchRequest = new SearchRequest(new String[]{"seven_product"}, sourceBuilder);
        return searchRequest;
    }

    private SearchResult buildSearchResult(SearchResponse searchResponse, SearchParam searchParam) {
        SearchResult result = new SearchResult();
        SearchHits hits = searchResponse.getHits();
        List<SkuEsModel> products = new ArrayList<>();
        if (hits.getHits() != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                SkuEsModel skuEsModel = JsonUtils.jsonToPojo(sourceAsString, SkuEsModel.class);
                if (StringUtils.isNotEmpty(searchParam.getKeyword())) {
                    HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                    skuEsModel.setSkuTitle(skuTitle.getFragments()[0].string());
                }
                products.add(skuEsModel);
            }
        }
        result.setProducts(products);

        List<SearchResult.AttrVo> attrs = new ArrayList<>();
        ParsedNested attrAgg = searchResponse.getAggregations().get("attr_agg");
        ParsedLongTerms attrIdAgg = attrAgg.getAggregations().get("attr_id_agg");
        for (Terms.Bucket bucket : attrIdAgg.getBuckets()) {
            SearchResult.AttrVo attrVo = new SearchResult.AttrVo();
            attrVo.setAttrId(bucket.getKeyAsNumber().longValue());
            ParsedStringTerms attrNameAgg = bucket.getAggregations().get("attr_name_agg");
            ParsedStringTerms attrValueAgg = bucket.getAggregations().get("attr_value_agg");
            attrVo.setAttrName(attrNameAgg.getBuckets().get(0).getKeyAsString());
            attrVo.setAttrValue(attrValueAgg.getBuckets().stream().map(MultiBucketsAggregation.Bucket::getKeyAsString).collect(Collectors.toList()));
            attrs.add(attrVo);
        }

        result.setAttrs(attrs);

        List<SearchResult.BrandVo> brands = new ArrayList<>();
        ParsedLongTerms brandAgg = searchResponse.getAggregations().get("brand_agg");
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            SearchResult.BrandVo brandVo = new SearchResult.BrandVo();
            brandVo.setBrandId(bucket.getKeyAsNumber().longValue());
            ParsedStringTerms brandNameAgg = bucket.getAggregations().get("brand_name_agg");
            ParsedStringTerms brandImgAgg = bucket.getAggregations().get("brand_img_agg");
            brandVo.setBrandName(brandNameAgg.getBuckets().get(0).getKeyAsString());
            brandVo.setBrandImg(brandImgAgg.getBuckets().get(0).getKeyAsString());
            brands.add(brandVo);
        }
        result.setBrands(brands);

        List<SearchResult.CatalogVo> catalogs = new ArrayList<>();
        ParsedLongTerms catalogAgg = searchResponse.getAggregations().get("catalog_agg");
        for (Terms.Bucket bucket : catalogAgg.getBuckets()) {
            SearchResult.CatalogVo catalogVo = new SearchResult.CatalogVo();
            catalogVo.setCatalogId(bucket.getKeyAsNumber().longValue());
            ParsedStringTerms catalogNameAgg = bucket.getAggregations().get("catalog_name_agg");
            catalogVo.setCatalogName(catalogNameAgg.getBuckets().get(0).getKeyAsString());
            catalogs.add(catalogVo);
        }
        result.setCatalogs(catalogs);
        long total = hits.getTotalHits().value;
        result.setTotal(total);
        result.setPageNum(searchParam.getPageNum());
        int totalPages = (int) (total % 2 == 0 ? total / 2 : (total / 2 + 1));
        result.setTotalPages(totalPages);
        return result;
    }


}

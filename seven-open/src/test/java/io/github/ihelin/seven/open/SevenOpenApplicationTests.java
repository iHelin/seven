package io.github.ihelin.seven.open;

import com.aliyun.oss.OSSClient;
import io.github.ihelin.seven.open.config.ESConfig;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class SevenOpenApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void searchData() throws IOException {
        SearchRequest searchRequest = new SearchRequest("users");
//        searchRequest.indices("users");

        SearchSourceBuilder searchRequestBuilder = new SearchSourceBuilder();
        searchRequestBuilder.query(QueryBuilders.matchQuery("address", "mill"));

        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchRequestBuilder.aggregation(ageAgg);

        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceceAvg").field("balance");
        searchRequestBuilder.aggregation(balanceAvg);

        searchRequest.source(searchRequestBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, ESConfig.COMMON_OPTIONS);
        System.out.println(searchResponse);
    }

    @Test
    void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        indexRequest.source("{\n" +
                "  \"name\": \"zhangshan\",\n" +
                "  \"gender\": \"男\",\n" +
                "  \"age\": 18\n" +
                "}", XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, ESConfig.COMMON_OPTIONS);
        System.out.println(indexResponse.toString());
    }

    @Test
    void esRest() {
        System.out.println(restHighLevelClient);
    }

    @Test
    public void testUpload() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("/Users/iHelin/Pictures/QQPlayer/11-6微服务认证方案04-“内部裸奔”改进方案_1608527276368.png");
        ossClient.putObject("ihelin", "改进方案_1608527276368.png", inputStream);
        ossClient.shutdown();
    }

}

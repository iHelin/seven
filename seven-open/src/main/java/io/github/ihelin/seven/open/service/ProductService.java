package io.github.ihelin.seven.open.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ihelin.seven.common.dto.SkuEsModel;
import io.github.ihelin.seven.open.config.ESConfig;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/15 下午5:12
 */
@Service
public class ProductService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            IndexRequest indexRequest = new IndexRequest("product");
            indexRequest.id(skuEsModel.getSkuId().toString());

            String skuString = objectMapper.writeValueAsString(skuEsModel);
            indexRequest.source(skuString, XContentType.JSON);

            bulkRequest.add(indexRequest);
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, ESConfig.COMMON_OPTIONS);

        boolean hasFailures = bulkResponse.hasFailures();
        if (hasFailures) {
            logger.error("商品上架错误，{}", bulkResponse.toString());
            return false;
        } else {
            return true;
        }

    }
}

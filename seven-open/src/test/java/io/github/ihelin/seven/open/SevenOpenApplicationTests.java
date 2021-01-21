package io.github.ihelin.seven.open;

import com.aliyun.oss.OSSClient;
import io.github.ihelin.seven.common.utils.HttpUtils;
import io.github.ihelin.seven.open.config.ESConfig;
import org.apache.http.HttpResponse;
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
import java.util.HashMap;
import java.util.Map;

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
    public void testSms() {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "e54dfdd6982e415c82f7c4f9500ea264";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("mobile", "18321558223");
        querys.put("param", "**验证码**:123455");
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "f5e68c3ad6b6474faa8cd178b21d3377");
        Map<String, String> bodys = new HashMap<>();

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

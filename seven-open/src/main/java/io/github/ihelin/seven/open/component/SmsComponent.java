package io.github.ihelin.seven.open.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title: SmsComponent</p>
 * Description：
 * date：2020/6/25 14:23
 */
@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
@Component
public class SmsComponent {

    private String url;
    private String smsSignId;
    private String templateId;
    private String appCode;

    @Autowired
    private RestTemplate restTemplate;

    public String sendSmsCode(String phone, String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "APPCODE " + appCode);
        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        return restTemplate.postForObject(url, request, String.class, phone, code, smsSignId, templateId);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSmsSignId() {
        return smsSignId;
    }

    public void setSmsSignId(String smsSignId) {
        this.smsSignId = smsSignId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}

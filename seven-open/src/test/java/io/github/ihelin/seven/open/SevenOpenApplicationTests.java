package io.github.ihelin.seven.open;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class SevenOpenApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OSSClient ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-shanghai.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
//        String accessKeyId = "LTAI4G4vjs2kdp8no5mu6pJ2";
//        String accessKeySecret = "rAtf9iaChsbnnxEbYnnpIU2hrjzW6X";

// 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream("/Users/iHelin/Pictures/QQPlayer/11-6微服务认证方案04-“内部裸奔”改进方案_1608527276368.png");
        ossClient.putObject("ihelin", "改进方案_1608527276368.png", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

}

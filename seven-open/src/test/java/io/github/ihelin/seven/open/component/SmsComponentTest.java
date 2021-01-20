package io.github.ihelin.seven.open.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author iHelin
 * @since 2021/1/20 20:15
 */
@SpringBootTest
class SmsComponentTest {

    @Autowired
    private SmsComponent smsComponent;

    @Test
    void sendSmsCode() {
        System.out.println(smsComponent.sendSmsCode("18321558223", "123465"));
    }
}
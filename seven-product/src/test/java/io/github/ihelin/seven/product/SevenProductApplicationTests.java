package io.github.ihelin.seven.product;

import io.github.ihelin.seven.product.entity.BrandEntity;
import io.github.ihelin.seven.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;

@SpringBootTest
class SevenProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Test
    void testRedis() {
        System.out.println(redisTemplate);
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello", "world" + UUID.randomUUID().toString());
        String hello = operations.get("hello");
        System.out.println(hello);
    }

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("小米");
        brandService.save(brandEntity);
    }

}

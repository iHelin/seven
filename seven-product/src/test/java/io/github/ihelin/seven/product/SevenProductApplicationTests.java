package io.github.ihelin.seven.product;

import io.github.ihelin.seven.product.entity.BrandEntity;
import io.github.ihelin.seven.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SevenProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("小米");
        brandService.save(brandEntity);
    }

}

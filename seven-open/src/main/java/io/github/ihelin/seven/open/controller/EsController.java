package io.github.ihelin.seven.open.controller;

import io.github.ihelin.seven.common.dto.SkuEsModel;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.open.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/15 下午5:10
 */
@RestController
@RequestMapping("/search")
public class EsController {

    @Autowired
    private ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @PostMapping("/save/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        try {
            productService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            logger.error("上架异常", e);
            return R.error();
        }
        return R.ok();
    }
}

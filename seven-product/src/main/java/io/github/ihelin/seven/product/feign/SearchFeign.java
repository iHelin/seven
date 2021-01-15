package io.github.ihelin.seven.product.feign;

import io.github.ihelin.seven.common.dto.SkuEsModel;
import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/15 下午5:29
 */
@FeignClient("seven-open")
public interface SearchFeign {

    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}

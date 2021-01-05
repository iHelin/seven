package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.CategoryEntity;

import java.util.Map;

/**
 * ÉÌÆ·Èý¼¶·ÖÀà
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-04 22:13:30
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.AttrAttrgroupRelationEntity;

import java.util.Map;

/**
 * ÊôÐÔ&ÊôÐÔ·Ö×é¹ØÁª
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-04 22:13:31
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


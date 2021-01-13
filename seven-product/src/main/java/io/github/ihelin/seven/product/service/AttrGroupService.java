package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.AttrGroupEntity;
import io.github.ihelin.seven.product.vo.AttrGroupWithAttrVo;

import java.util.List;
import java.util.Map;

/**
 * pms_attr_group
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Integer catelogId);

    List<AttrGroupWithAttrVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);
}


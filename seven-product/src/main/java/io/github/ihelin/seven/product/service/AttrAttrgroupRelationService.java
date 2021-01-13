package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.AttrAttrgroupRelationEntity;
import io.github.ihelin.seven.product.vo.AttrGroupRelationVo;

import java.util.List;
import java.util.Map;

/**
 * pms_attr_attrgroup_relation
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void deleteRelation(List<AttrGroupRelationVo> vos);

    void addRelations(List<AttrGroupRelationVo> vos);
}


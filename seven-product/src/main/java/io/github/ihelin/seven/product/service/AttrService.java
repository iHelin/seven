package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.AttrEntity;
import io.github.ihelin.seven.product.vo.AttrRespVo;
import io.github.ihelin.seven.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * pms_attr
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Long catalogId, Map<String, Object> params, String attrType);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrGroupId);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrGroupId);
}


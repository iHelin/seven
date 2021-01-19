package io.github.ihelin.seven.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.ihelin.seven.product.entity.AttrGroupEntity;
import io.github.ihelin.seven.product.vo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * pms_attr_group
 * 
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}

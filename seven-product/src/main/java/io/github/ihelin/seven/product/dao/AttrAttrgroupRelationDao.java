package io.github.ihelin.seven.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.ihelin.seven.product.entity.AttrAttrgroupRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * pms_attr_attrgroup_relation
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBatchRelaton(@Param("relationEntities") List<AttrAttrgroupRelationEntity> relationEntities);
}

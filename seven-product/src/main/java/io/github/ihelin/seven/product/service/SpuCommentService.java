package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * pms_spu_comment
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:40
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


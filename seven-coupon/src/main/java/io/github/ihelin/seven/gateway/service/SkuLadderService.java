package io.github.ihelin.seven.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.common.utils.PageUtils;
import io.github.ihelin.seven.gateway.entity.SkuLadderEntity;

import java.util.Map;

/**
 * ÉÌÆ·½×ÌÝ¼Û¸ñ
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


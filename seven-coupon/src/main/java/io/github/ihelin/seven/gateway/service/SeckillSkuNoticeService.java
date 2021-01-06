package io.github.ihelin.seven.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.common.utils.PageUtils;
import io.github.ihelin.seven.gateway.entity.SeckillSkuNoticeEntity;

import java.util.Map;

/**
 * ÃëÉ±ÉÌÆ·Í¨Öª¶©ÔÄ
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


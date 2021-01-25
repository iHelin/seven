package io.github.ihelin.seven.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.dto.SkuHasStockVo;
import io.github.ihelin.seven.common.dto.mq.OrderTo;
import io.github.ihelin.seven.common.dto.mq.StockLockedTo;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.ware.entity.WareSkuEntity;
import io.github.ihelin.seven.ware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * ÉÌÆ·¿â´æ
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:18:39
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    Boolean orderLockStock(WareSkuLockVo vo);

    /**
     * 由于订单超时而自动释放订单之后来解锁库存
     */
    void unlockStock(OrderTo to);

    void unlockStock(StockLockedTo stockLockedTo);
}


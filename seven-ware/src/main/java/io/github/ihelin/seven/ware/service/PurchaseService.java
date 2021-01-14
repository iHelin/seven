package io.github.ihelin.seven.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.ware.entity.PurchaseEntity;
import io.github.ihelin.seven.ware.vo.MergeVo;
import io.github.ihelin.seven.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * ²É¹ºÐÅÏ¢
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:18:39
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryUnReceivePage(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void finish(PurchaseDoneVo purchaseDoneVo);
}


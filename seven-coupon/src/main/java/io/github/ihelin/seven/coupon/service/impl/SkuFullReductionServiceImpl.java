package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.dto.MemberPrice;
import io.github.ihelin.seven.common.dto.SkuReductionDTO;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.SkuFullReductionDao;
import io.github.ihelin.seven.coupon.entity.MemberPriceEntity;
import io.github.ihelin.seven.coupon.entity.SkuFullReductionEntity;
import io.github.ihelin.seven.coupon.entity.SkuLadderEntity;
import io.github.ihelin.seven.coupon.service.MemberPriceService;
import io.github.ihelin.seven.coupon.service.SkuFullReductionService;
import io.github.ihelin.seven.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* sms_sku_full_reduction
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Autowired
    private SkuLadderService skuLadderService;
    @Autowired
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveReduction(SkuReductionDTO skuReductionDTO) {
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skuReductionDTO.getSkuId());
        skuLadderEntity.setFullCount(skuReductionDTO.getFullCount());
        skuLadderEntity.setDiscount(skuReductionDTO.getDiscount());
        skuLadderEntity.setAddOther(skuReductionDTO.getCountStatus());
        if (skuReductionDTO.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }

        if (skuReductionDTO.getFullPrice().compareTo(BigDecimal.ZERO) > 0) {
            SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
            BeanUtils.copyProperties(skuReductionDTO, skuFullReductionEntity);
            this.save(skuFullReductionEntity);
        }


        List<MemberPrice> memberPrice = skuReductionDTO.getMemberPrice();
        List<MemberPriceEntity> memberPriceEntities = memberPrice.stream().map(item -> {
            MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
            memberPriceEntity.setSkuId(skuReductionDTO.getSkuId());
            memberPriceEntity.setMemberLevelId(item.getId());
            memberPriceEntity.setMemberLevelName(item.getName());
            memberPriceEntity.setMemberPrice(item.getPrice());
            memberPriceEntity.setAddOther(1);
            return memberPriceEntity;
        }).filter(item-> item.getMemberPrice().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
        memberPriceService.saveBatch(memberPriceEntities);
    }

}
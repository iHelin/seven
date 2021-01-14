package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.SeckillPromotionDao;
import io.github.ihelin.seven.coupon.entity.SeckillPromotionEntity;
import io.github.ihelin.seven.coupon.service.SeckillPromotionService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_seckill_promotion
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionDao, SeckillPromotionEntity> implements SeckillPromotionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillPromotionEntity> page = this.page(
                new Query<SeckillPromotionEntity>().getPage(params),
                new QueryWrapper<SeckillPromotionEntity>()
        );

        return new PageUtils(page);
    }

}
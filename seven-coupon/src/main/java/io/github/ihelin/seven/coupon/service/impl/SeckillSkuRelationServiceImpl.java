package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.SeckillSkuRelationDao;
import io.github.ihelin.seven.coupon.entity.SeckillSkuRelationEntity;
import io.github.ihelin.seven.coupon.service.SeckillSkuRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_seckill_sku_relation
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity> implements SeckillSkuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSkuRelationEntity> page = this.page(
                new Query<SeckillSkuRelationEntity>().getPage(params),
                new QueryWrapper<SeckillSkuRelationEntity>()
        );

        return new PageUtils(page);
    }

}
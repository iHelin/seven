package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.SeckillSkuNoticeDao;
import io.github.ihelin.seven.coupon.entity.SeckillSkuNoticeEntity;
import io.github.ihelin.seven.coupon.service.SeckillSkuNoticeService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_seckill_sku_notice
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeDao, SeckillSkuNoticeEntity> implements SeckillSkuNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSkuNoticeEntity> page = this.page(
                new Query<SeckillSkuNoticeEntity>().getPage(params),
                new QueryWrapper<SeckillSkuNoticeEntity>()
        );

        return new PageUtils(page);
    }

}
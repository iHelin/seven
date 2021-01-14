package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.CouponHistoryDao;
import io.github.ihelin.seven.coupon.entity.CouponHistoryEntity;
import io.github.ihelin.seven.coupon.service.CouponHistoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_coupon_history
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryDao, CouponHistoryEntity> implements CouponHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponHistoryEntity> page = this.page(
                new Query<CouponHistoryEntity>().getPage(params),
                new QueryWrapper<CouponHistoryEntity>()
        );

        return new PageUtils(page);
    }

}
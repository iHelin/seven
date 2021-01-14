package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.CouponDao;
import io.github.ihelin.seven.coupon.entity.CouponEntity;
import io.github.ihelin.seven.coupon.service.CouponService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_coupon
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponEntity> page = this.page(
                new Query<CouponEntity>().getPage(params),
                new QueryWrapper<CouponEntity>()
        );

        return new PageUtils(page);
    }

}
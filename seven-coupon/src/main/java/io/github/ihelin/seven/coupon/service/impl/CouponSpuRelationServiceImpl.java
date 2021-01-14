package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.CouponSpuRelationDao;
import io.github.ihelin.seven.coupon.entity.CouponSpuRelationEntity;
import io.github.ihelin.seven.coupon.service.CouponSpuRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_coupon_spu_relation
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationDao, CouponSpuRelationEntity> implements CouponSpuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuRelationEntity> page = this.page(
                new Query<CouponSpuRelationEntity>().getPage(params),
                new QueryWrapper<CouponSpuRelationEntity>()
        );

        return new PageUtils(page);
    }

}
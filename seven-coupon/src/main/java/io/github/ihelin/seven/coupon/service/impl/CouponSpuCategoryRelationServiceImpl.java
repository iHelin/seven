package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.CouponSpuCategoryRelationDao;
import io.github.ihelin.seven.coupon.entity.CouponSpuCategoryRelationEntity;
import io.github.ihelin.seven.coupon.service.CouponSpuCategoryRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_coupon_spu_category_relation
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationDao, CouponSpuCategoryRelationEntity> implements CouponSpuCategoryRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuCategoryRelationEntity> page = this.page(
                new Query<CouponSpuCategoryRelationEntity>().getPage(params),
                new QueryWrapper<CouponSpuCategoryRelationEntity>()
        );

        return new PageUtils(page);
    }

}
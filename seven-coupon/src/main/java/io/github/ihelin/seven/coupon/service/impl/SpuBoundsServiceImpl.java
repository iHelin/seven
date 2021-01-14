package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.SpuBoundsDao;
import io.github.ihelin.seven.coupon.entity.SpuBoundsEntity;
import io.github.ihelin.seven.coupon.service.SpuBoundsService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_spu_bounds
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:57
*/
@Service
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity> implements SpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuBoundsEntity> page = this.page(
                new Query<SpuBoundsEntity>().getPage(params),
                new QueryWrapper<SpuBoundsEntity>()
        );

        return new PageUtils(page);
    }

}
package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.SkuLadderDao;
import io.github.ihelin.seven.coupon.entity.SkuLadderEntity;
import io.github.ihelin.seven.coupon.service.SkuLadderService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_sku_ladder
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderDao, SkuLadderEntity> implements SkuLadderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuLadderEntity> page = this.page(
                new Query<SkuLadderEntity>().getPage(params),
                new QueryWrapper<SkuLadderEntity>()
        );

        return new PageUtils(page);
    }

}
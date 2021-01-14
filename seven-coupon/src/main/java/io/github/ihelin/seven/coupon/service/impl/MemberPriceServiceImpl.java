package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.MemberPriceDao;
import io.github.ihelin.seven.coupon.entity.MemberPriceEntity;
import io.github.ihelin.seven.coupon.service.MemberPriceService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_member_price
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceDao, MemberPriceEntity> implements MemberPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberPriceEntity> page = this.page(
                new Query<MemberPriceEntity>().getPage(params),
                new QueryWrapper<MemberPriceEntity>()
        );

        return new PageUtils(page);
    }

}
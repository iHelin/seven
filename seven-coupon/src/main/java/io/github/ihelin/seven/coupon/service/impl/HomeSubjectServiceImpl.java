package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.HomeSubjectDao;
import io.github.ihelin.seven.coupon.entity.HomeSubjectEntity;
import io.github.ihelin.seven.coupon.service.HomeSubjectService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_home_subject
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectDao, HomeSubjectEntity> implements HomeSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectEntity> page = this.page(
                new Query<HomeSubjectEntity>().getPage(params),
                new QueryWrapper<HomeSubjectEntity>()
        );

        return new PageUtils(page);
    }

}
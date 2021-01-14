package io.github.ihelin.seven.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.coupon.dao.HomeSubjectSpuDao;
import io.github.ihelin.seven.coupon.entity.HomeSubjectSpuEntity;
import io.github.ihelin.seven.coupon.service.HomeSubjectSpuService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* sms_home_subject_spu
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@Service
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity> implements HomeSubjectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                new Query<HomeSubjectSpuEntity>().getPage(params),
                new QueryWrapper<HomeSubjectSpuEntity>()
        );

        return new PageUtils(page);
    }

}
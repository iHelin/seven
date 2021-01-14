package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.order.dao.RefundInfoDao;
import io.github.ihelin.seven.order.entity.RefundInfoEntity;
import io.github.ihelin.seven.order.service.RefundInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* oms_refund_info
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity> implements RefundInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RefundInfoEntity> page = this.page(
                new Query<RefundInfoEntity>().getPage(params),
                new QueryWrapper<RefundInfoEntity>()
        );

        return new PageUtils(page);
    }

}
package io.github.ihelin.seven.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.ware.dao.WareInfoDao;
import io.github.ihelin.seven.ware.entity.WareInfoEntity;
import io.github.ihelin.seven.ware.feign.MemberFeign;
import io.github.ihelin.seven.ware.service.WareInfoService;
import io.github.ihelin.seven.ware.vo.FareVo;
import io.github.ihelin.seven.ware.vo.MemberAddressVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Autowired
    private MemberFeign memberFeign;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> queryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.eq("id", key)
                .or().like("name", key)
                .or().like("address", key)
                .or().like("areacode", key);
        }
        IPage<WareInfoEntity> page = this.page(
            new Query<WareInfoEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public FareVo getFare(Long addrId) {
        R info = memberFeign.addrInfo(addrId);
        FareVo fareVo = new FareVo();
        MemberAddressVo addressVo = info.getData(new TypeReference<MemberAddressVo>() {
        });
        fareVo.setMemberAddressVo(addressVo);
        if (addressVo != null) {
            String phone = addressVo.getPhone();
            if (phone == null || phone.length() < 2) {
                phone = new Random().nextInt(100) + "";
            }
            BigDecimal decimal = new BigDecimal(phone.substring(phone.length() - 1));
            fareVo.setFare(decimal);
        } else {
            fareVo.setFare(new BigDecimal("20"));
        }
        return fareVo;
    }

}
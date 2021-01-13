package io.github.ihelin.seven.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.product.dao.SkuInfoDao;
import io.github.ihelin.seven.product.entity.SkuInfoEntity;
import io.github.ihelin.seven.product.service.SkuInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * pms_sku_info
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            wrapper.and(w -> {
                w.eq("sku_id", key).or().like("sku_name", key).or().like("sku_title", key);
            });
        }
        String catalogId = (String) params.get("catalogId");
        if (StringUtils.isNotEmpty(catalogId)) {
            wrapper.eq("catalog_id", catalogId);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotEmpty(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        String min = (String) params.get("min");
        if (StringUtils.isNotEmpty(min)) {
            wrapper.ge("price", min);
        }
        String max = (String) params.get("max");
        if (StringUtils.isNotEmpty(max) && !"0".equalsIgnoreCase(max)) {
            wrapper.le("price", max);
        }

        IPage<SkuInfoEntity> page = this.page(new Query<SkuInfoEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

}
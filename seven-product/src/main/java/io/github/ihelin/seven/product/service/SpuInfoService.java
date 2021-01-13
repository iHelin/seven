package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.SpuInfoEntity;
import io.github.ihelin.seven.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * pms_spu_info
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:40
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo spuSaveVo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

}


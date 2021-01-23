package io.github.ihelin.seven.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.ware.entity.WareInfoEntity;
import io.github.ihelin.seven.ware.vo.FareVo;

import java.util.Map;

/**
 * ²Ö¿âÐÅÏ¢
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:18:39
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据收获地址计算运费
     */
    FareVo getFare(Long addrId);
}


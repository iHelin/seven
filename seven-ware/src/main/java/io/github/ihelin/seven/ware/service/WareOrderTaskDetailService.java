package io.github.ihelin.seven.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * ¿â´æ¹¤×÷µ¥
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:18:39
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


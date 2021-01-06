package io.github.ihelin.seven.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * »ý·Ö±ä»¯ÀúÊ·¼ÇÂ¼
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:53:16
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


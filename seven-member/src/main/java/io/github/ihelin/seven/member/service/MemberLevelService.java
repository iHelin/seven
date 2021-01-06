package io.github.ihelin.seven.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * »áÔ±µÈ¼¶
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:53:16
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


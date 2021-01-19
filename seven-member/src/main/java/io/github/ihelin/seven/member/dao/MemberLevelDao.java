package io.github.ihelin.seven.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.ihelin.seven.member.entity.MemberLevelEntity;

/**
 * »áÔ±µÈ¼¶
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-05 12:53:16
 */
public interface MemberLevelDao extends BaseMapper<MemberLevelEntity> {

    MemberLevelEntity getDefaultLevel();
}

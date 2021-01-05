package io.github.ihelin.seven.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.common.utils.PageUtils;
import io.github.ihelin.seven.member.entity.MemberEntity;

import java.util.Map;

/**
 * »áÔ±
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:53:16
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


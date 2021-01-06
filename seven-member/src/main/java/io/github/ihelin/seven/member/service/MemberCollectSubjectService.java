package io.github.ihelin.seven.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.member.entity.MemberCollectSubjectEntity;

import java.util.Map;

/**
 * »áÔ±ÊÕ²ØµÄ×¨Ìâ»î¶¯
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:53:16
 */
public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


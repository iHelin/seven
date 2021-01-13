package io.github.ihelin.seven.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


/**
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-05 12:53:16
 */
@TableName("ums_member_collect_subject")
public class MemberCollectSubjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	private Long subjectId;
	private String subjectName;
	private String subjectImg;
	private String subjectUrll;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectImg() {
		return subjectImg;
	}

	public void setSubjectImg(String subjectImg) {
		this.subjectImg = subjectImg;
	}

	public String getSubjectUrll() {
		return subjectUrll;
	}

	public void setSubjectUrll(String subjectUrll) {
		this.subjectUrll = subjectUrll;
	}
}

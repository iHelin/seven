package io.github.ihelin.seven.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
//import lombok.Data;

/**
 * »áÔ±
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:53:16
 */
//@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * »áÔ±µÈ¼¶id
	 */
	private Long levelId;
	/**
	 * ÓÃ»§Ãû
	 */
	private String username;
	/**
	 * ÃÜÂë
	 */
	private String password;
	/**
	 * êÇ³Æ
	 */
	private String nickname;
	/**
	 * ÊÖ»úºÅÂë
	 */
	private String mobile;
	/**
	 * ÓÊÏä
	 */
	private String email;
	/**
	 * Í·Ïñ
	 */
	private String header;
	/**
	 * ÐÔ±ð
	 */
	private Integer gender;
	/**
	 * ÉúÈÕ
	 */
	private Date birth;
	/**
	 * ËùÔÚ³ÇÊÐ
	 */
	private String city;
	/**
	 * Ö°Òµ
	 */
	private String job;
	/**
	 * ¸öÐÔÇ©Ãû
	 */
	private String sign;
	/**
	 * ÓÃ»§À´Ô´
	 */
	private Integer sourceType;
	/**
	 * »ý·Ö
	 */
	private Integer integration;
	/**
	 * ³É³¤Öµ
	 */
	private Integer growth;
	/**
	 * ÆôÓÃ×´Ì¬
	 */
	private Integer status;
	/**
	 * ×¢²áÊ±¼ä
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getIntegration() {
		return integration;
	}

	public void setIntegration(Integer integration) {
		this.integration = integration;
	}

	public Integer getGrowth() {
		return growth;
	}

	public void setGrowth(Integer growth) {
		this.growth = growth;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

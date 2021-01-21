package io.github.ihelin.seven.member.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author iHelin
 */
public class SocialUser {

    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("remind_in")
    private String remindIn;

    @JsonAlias("expires_in")
    private Long expiresIn;

    private String uid;

    private String isRealName;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRemindIn() {
        return remindIn;
    }

    public void setRemindIn(String remindIn) {
        this.remindIn = remindIn;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }
}
package io.github.ihelin.seven.member.vo;

public class SocialUser {

    private String accessToken;

    private String remindIn;

    private int expiresIn;

    private String uid;

    private String isrealname;

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

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsrealname() {
        return isrealname;
    }

    public void setIsrealname(String isrealname) {
        this.isrealname = isrealname;
    }
}
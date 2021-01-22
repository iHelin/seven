package io.github.ihelin.seven.product.vo;

/**
 * @author iHelin
 */
public class UserInfoTo {

    /**
     * 存储已登录用户在数据库中的ID
     */
    private Long userId;

    /**
     * 存储用户名
     */
    private String username;

    /**
     * 配分一个临时的user-key
     */
    private String userKey;

    /**
     * 判断是否是临时用户
     */
    private boolean tempUser = false;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public boolean isTempUser() {
        return tempUser;
    }

    public void setTempUser(boolean tempUser) {
        this.tempUser = tempUser;
    }
}

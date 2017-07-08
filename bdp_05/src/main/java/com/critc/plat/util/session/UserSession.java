package com.critc.plat.util.session;

import java.io.Serializable;

/**
 * 系统用户Session信息 该类可以根据实际信息进行修改
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1629527703944211785L;
    private int userId;//用户id
    private String userIp;//用户IP

    private String username;//用户名  即登录账号
    private String realname;//真实姓名
    private int roleId;//角色id
    private String roleName;//角色名称

    @Override
    public String toString() {
        return "UserSession{" +
                "userId=" + userId +
                ", userIp='" + userIp + '\'' +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

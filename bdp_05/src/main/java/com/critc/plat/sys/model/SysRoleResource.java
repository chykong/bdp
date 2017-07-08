package com.critc.plat.sys.model;

/**
 * 系统角色对应资源
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class SysRoleResource {
    private int id;//id
    private int roleId;//角色id
    private int resourceId;//资源id
    private String resourceCode;//角色代码
    private String url;//链接

    @Override
    public String toString() {
        return "SysRoleResource{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                ", resourceCode='" + resourceCode + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}

package com.critc.plat.sys.model;

import java.util.Date;

/**
 * 操作日志
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class SysLog {
    private long id;
    private int userId;//用户id
    private Date operaDate;//操作日期
    private String operaIp;//ip地址
    private String moduleName;//模块id
    private String operaName;//操作名称
    private String operaUrl;//操作url
    private String operaParams;//参数
    private String realname;//用户姓名

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", operaDate=" + operaDate +
                ", operaIp='" + operaIp + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", operaName='" + operaName + '\'' +
                ", operaUrl='" + operaUrl + '\'' +
                ", operaParams='" + operaParams + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOperaDate() {
        return operaDate;
    }

    public void setOperaDate(Date operaDate) {
        this.operaDate = operaDate;
    }

    public String getOperaIp() {
        return operaIp;
    }

    public void setOperaIp(String operaIp) {
        this.operaIp = operaIp;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperaName() {
        return operaName;
    }

    public void setOperaName(String operaName) {
        this.operaName = operaName;
    }

    public String getOperaUrl() {
        return operaUrl;
    }

    public void setOperaUrl(String operaUrl) {
        this.operaUrl = operaUrl;
    }

    public String getOperaParams() {
        return operaParams;
    }

    public void setOperaParams(String operaParams) {
        this.operaParams = operaParams;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}

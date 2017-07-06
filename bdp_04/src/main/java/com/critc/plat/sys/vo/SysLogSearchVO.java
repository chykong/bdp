package com.critc.plat.sys.vo;


import com.critc.plat.util.page.PageSearchVO;

/**
 * 日志管理查询条件
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class SysLogSearchVO extends PageSearchVO {
    private Integer userId;//用户id
    private String startDate;//起始日期
    private String endDate;//终止日期

    @Override
    public String toString() {
        return "SysLogSearchVO{" +
                "userId=" + userId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

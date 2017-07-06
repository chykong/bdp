package com.critc.plat.sys.vo;

import com.critc.plat.util.page.PageSearchVO;

/**
 * 用户登录查询条件
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class SysUserloginSearchVO extends PageSearchVO {
    private Integer userId;//用户ID

    @Override
    public String toString() {
        return "SysUserloginSearchVO{" +
                "userId=" + userId +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

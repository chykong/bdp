package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.SysUserLoginDao;
import com.critc.plat.sys.model.SysUserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * 系统登录Service
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Service
@Configuration
@EnableAsync
public class SysLoginService {
    @Autowired
    private SysUserLoginDao sysUserLoginDao;

    /**
     * 登录时新增登录信息,异步处理
     *
     * @param sysUserLogin
     * @return
     */
    @Async
    public void add(SysUserLogin sysUserLogin) {
        sysUserLoginDao.add(sysUserLogin);
    }

}

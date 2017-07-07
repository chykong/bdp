package com.critc.plat.sys.controller;

import com.critc.plat.core.pub.PubConfig;
import com.critc.plat.sys.model.SysUser;
import com.critc.plat.sys.model.SysUserLogin;
import com.critc.plat.sys.service.SysLoginService;
import com.critc.plat.sys.service.SysUserService;
import com.critc.plat.util.json.JsonUtil;
import com.critc.plat.util.session.SessionUtil;
import com.critc.plat.util.session.UserSession;
import com.critc.plat.util.string.StringUtil;
import com.critc.plat.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 系统登录校验，及首页显示
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@RequestMapping("/")
@Controller
public class SysLoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysLoginService userLoginService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入系统登录界面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/login");
        return mv;
    }

    /**
     * 登录校验
     *
     * @param request
     * @param response
     * @param username
     * @param password
     */
    @RequestMapping("/checkLogin")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        //		// 校验账号和密码是否都为空
        if (StringUtil.isNullOrEmpty(username) || StringUtil.isNullOrEmpty(password)) {
            WebUtil.out(response, JsonUtil.createOperaStr(false, "用户名或密码错误"));
            return;
        }

        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null || !sysUserService.checkPass(sysUser, password)) {
            String json = "{\"success\":" + false + ",\"msgText\":\"用户名或密码错误\"}";
            WebUtil.out(response, json);
        } else {
            if (sysUser.getStatus() == 2) {
                WebUtil.out(response, JsonUtil.createOperaStr(false, "该用户已锁定"));
            } else {
                String ip = StringUtil.getIp(request);
                UserSession userSession = new UserSession();
                userSession.setUserId(sysUser.getId());//userId
                userSession.setUsername(sysUser.getUsername());//username
                userSession.setUserIp(ip);//IP地址
                userSession.setRoleId(sysUser.getRoleId());
                userSession.setRoleName(sysUser.getRoleName());// 角色
                userSession.setRealname(sysUser.getRealname());
                request.getSession().setAttribute("userSession", userSession);

                request.getSession().setMaxInactiveInterval(1000 * 60 * 30);// 设置过期时间30分钟
                // 插入登录日志
                SysUserLogin sysUserLogin = new SysUserLogin();
                sysUserLogin.setUserId(sysUser.getId());
                sysUserLogin.setLoginDate(new Date());
                sysUserLogin.setLoginIp(ip);
                sysUserLogin.setTerminal(WebUtil.getSafeStr(request.getParameter("terminal")));
                sysUserLogin.setExplorerType(WebUtil.getSafeStr(request.getParameter("explorerType")));
                sysUserLogin.setExplorerVersion(WebUtil.getSafeStr(request.getParameter("explorerVersion")));
                userLoginService.add(sysUserLogin);
                WebUtil.out(response, JsonUtil.createOperaStr(true, "登录成功"));
            }
        }
    }


    /**
     * 进入首页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/index");
        return mv;
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/login.htm";
    }

    /**
     * 进入修改密码界面
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequestMapping("/toUpdatePass")
    public ModelAndView toUpdatePass(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/sys/user/updatePass");
        mv.addObject("backUrl", pubConfig.getDynamicServer() + "/index.htm");
        return mv;
    }

    /**
     * 修改个人信息，登录页面
     */
    @RequestMapping("/updatePass")
    public String updatePass(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        String oldPass = WebUtil.getSafeStr(request.getParameter("oldPass"));
        String newPass = WebUtil.getSafeStr(request.getParameter("newPass"));
        UserSession userSession = SessionUtil.getUserSession(request);
        int flag = sysUserService.updatePass(userSession.getUserId(), oldPass, newPass);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("密码修改失败");
        else if (flag == 2)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("原密码输入错误");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("密码修改成功");
    }

}

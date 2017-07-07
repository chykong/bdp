package com.critc.plat.util.session;

import javax.servlet.http.HttpServletRequest;

/**
 * session工具类，用于获取用户session
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class SessionUtil {
    /**
     * 功能描述:获取用户session
     *
     * @param request
     * @return UserSession
     * @version 1.0.0
     */
    public static UserSession getUserSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("userSession") != null)
            return (UserSession) request.getSession().getAttribute("userSession");
        else {
//            UserSession userSession = new UserSession();
//            userSession.setRoleId(1);
//            userSession.setUserId(1);
//            userSession.setUsername("admin");
//            userSession.setRealname("admin");
//            return userSession;
            return null;
        }
    }

    /**
     * 获取当前操作人
     *
     * @param request
     * @return
     */
    public static String getRealname(HttpServletRequest request) {
        return getUserSession(request).getRealname();
    }
}

package com.critc.plat.core.interceptor;

import com.critc.plat.util.json.JsonUtil;
import com.critc.plat.util.session.SessionUtil;
import com.critc.plat.util.session.UserSession;
import com.critc.plat.util.string.StringUtil;
import com.critc.plat.util.web.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:校验是否登录拦截器
 * 未登录直接跳转至登录页面
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class CheckLoginInterceptor implements HandlerInterceptor {

    /**
     * 操作前先判断是否登录，未登录跳转到登录界面
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserSession userSession = SessionUtil.getUserSession(request);
        if (userSession == null) {
            boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);
            if (isAjaxRequest) {
                WebUtil.out(response, JsonUtil.createOperaStr(false, "连接超时，请重新登录!"));
            } else {
                String str = "<script>top.location.href='" + request.getContextPath() + "/'</script>";
                WebUtil.out(response, str);
            }
            return false;
        } else {
            return true;
        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

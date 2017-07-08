package com.critc.plat.core.interceptor;

import com.critc.plat.core.pub.PubConfig;
import com.critc.plat.sys.service.SysLogService;
import com.critc.plat.sys.service.SysRoleService;
import com.critc.plat.util.date.DateUtil;
import com.critc.plat.util.json.JsonUtil;
import com.critc.plat.util.session.SessionUtil;
import com.critc.plat.util.session.UserSession;
import com.critc.plat.util.string.StringUtil;
import com.critc.plat.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统权限控制拦截器，访问每个操作时先进行权限校验。
 * 同时记录下日志
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private PubConfig pubConfig;

    private static Logger logger = LoggerFactory.getLogger("operationLog");

    /**
     * 校验权限，如果无权限，提示权限不足
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserSession userSession = SessionUtil.getUserSession(request);
        //校验权限
        String path = request.getServletPath();
        path = path.substring(1, path.length());
        String operaMethod = path.substring(path.lastIndexOf("/"));
        operaMethod = operaMethod.substring(1, operaMethod.length());
        String parameters = StringUtil.getOperaParams(request);
        //记操作日志
        logOperation(path, parameters, userSession);
        //目前只校验add/update/delete/save/import开头的方法，其余不校验
        boolean checked = sysRoleService.checkAuthority(userSession.getRoleId(), path);
        if (checked) {
            if (checkUrl(operaMethod)) {
                //记录数据库日志
                sysLogService.addLog(userSession.getUserId(), path, parameters, userSession.getUserIp());
            }
            return true;
        } else {
            boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);
            if (isAjaxRequest) {
                WebUtil.out(response, JsonUtil.createOperaStr(false, "权限不足"));
            } else {
                String location = pubConfig.getDynamicServer() + "/error.htm?msg=" + StringUtil.encodeUrl("权限不足");
                String str = "<script>location.href='" + location + "';</script>";
                WebUtil.out(response, str);
            }
            return false;
        }
    }

    /**
     * 记录文本日志
     *
     * @param path
     * @param parameters
     * @param us
     */
    public void logOperation(String path, String parameters, UserSession us) {
        String log = "";
        log = "[OPERALOG]" + "-[" + us.getUserIp() + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[" + us.getUsername() + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private static boolean checkUrl(String url) {
        Pattern pattern = Pattern.compile("^(add|update|delete|save|import).*");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static void main(String[] args) {
        //当条件满足时，将返回true，否则返回false
        System.out.println(AuthorityInterceptor.checkUrl("aupdate.htm"));
    }
}

package com.critc.plat.util.string;


import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面返回url工具 由于所有页面都是直接跳转，必须得把当前页面的查询条件一起带到下个页面，所以必须把返回的url进行页面间参数传递
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class BackUrlUtil {

    /**
     * 在index页面生成返回的backUrl
     * @param mv
     * @param request
     * @param url
     */
    public static void createBackUrl(ModelAndView mv, HttpServletRequest request, String url) {
        if (request.getParameter("pageIndex") != null)
            url += "&pageIndex=" + request.getParameter("pageIndex");
        url = StringUtil.encodeUrl(url);
        mv.addObject("backUrl", url);
    }

    /**
     * 在add或update页面设置backurl,encode一下
     *
     * @param mv
     * @param request
     */
    public static void setBackUrl(ModelAndView mv, HttpServletRequest request) {
        String url = request.getParameter("backUrl");
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
    }

    /**
     * 获取返回的url
     *
     * @param request
     * @return
     */
    public static String getBackUrl(HttpServletRequest request) {
        String backUrl = request.getParameter("backUrl");
        if (backUrl != null)
            backUrl = StringUtil.decodeUrl(backUrl);
        return backUrl;
    }

}


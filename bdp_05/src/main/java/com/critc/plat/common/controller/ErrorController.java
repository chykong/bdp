package com.critc.plat.common.controller;

import com.critc.plat.util.string.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 操作失败controller，用于全局统一处理失败操作
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
@Controller
@RequestMapping("/")
public class ErrorController {

    /**
     * 异常处理
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/common/error");
        mv.addObject("msg", StringUtil.decodeUrl(request.getParameter("msg")));
        mv.addObject("backUrl", StringUtil.decodeUrl(request.getParameter("backUrl")));
        return mv;
    }
}

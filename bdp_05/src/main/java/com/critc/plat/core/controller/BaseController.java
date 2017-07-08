package com.critc.plat.core.controller;

import com.critc.plat.util.json.JsonUtil;
import com.critc.plat.util.string.StringUtil;
import com.critc.plat.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础Controller，用于异常处理
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger("controllerLog");

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        logger.error("请求地址：" + request.getServletPath());
        logger.error("请求参数：" + StringUtil.getOperaParams(request));
        logger.error("异常：" + ex.getMessage());
        //判断是否是Ajax请求
        boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
        //获取异常的详细信息
        if (isAjaxRequest) {
            String msg = "{\"flag\":false,\"msg\":" + ex.getMessage() + "}";
            WebUtil.out(response, JsonUtil.toStr(msg));
            return null;
        } else {
            //URL请求处理
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", ex.getMessage());
            map.put("isError", true);
            map.put("exceptionName", ex.getMessage());
            return new ModelAndView("/plat/common/exception", map);
        }
    }


}

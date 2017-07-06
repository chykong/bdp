package com.critc.plat.common.controller;

import com.critc.plat.util.string.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 操作成功处理controller
 * @author 孔垂云
 * @date 2017-05-23
 */
@Controller
@RequestMapping("/")
public class SuccessController {

	/**
	 * 成功处理操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/success")
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/plat/common/success");
		mv.addObject("msg", StringUtil.decodeUrl(request.getParameter("msg")));
		mv.addObject("backUrl", StringUtil.decodeUrl(request.getParameter("backUrl")));
		return mv;
	}
}

package com.critc.plat.sys.controller;

import com.critc.plat.core.controller.BaseController;
import com.critc.plat.core.pub.PubConfig;
import com.critc.plat.sys.model.SysLog;
import com.critc.plat.sys.service.SysLogService;
import com.critc.plat.sys.service.SysUserService;
import com.critc.plat.sys.vo.SysLogSearchVO;
import com.critc.plat.util.date.DateUtil;
import com.critc.plat.util.page.PageNavigate;
import com.critc.plat.util.string.BackUrlUtil;
import com.critc.plat.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统日志查询Controller
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入日志查看界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, SysLogSearchVO sysLogSearchVO) {
        if (StringUtil.isNullOrEmpty(sysLogSearchVO.getStartDate()))//设置查询起始日期
            sysLogSearchVO.setStartDate(DateUtil.getOperaDate(DateUtil.getSystemDate(), -30));
        if (StringUtil.isNullOrEmpty(sysLogSearchVO.getEndDate()))//设置截止日期
            sysLogSearchVO.setEndDate(DateUtil.getSystemDate());
        ModelAndView mv = new ModelAndView();
        int recordCount = sysLogService.count(sysLogSearchVO);// 获取查询总数
        String url = createUrl(sysLogSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, sysLogSearchVO.getPageIndex(), recordCount);//
        List<SysLog> list = sysLogService.list(sysLogSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.addObject("listUser", sysUserService.listAll());// 用户列表
        mv.setViewName("/plat/sys/log/index");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(SysLogSearchVO sysLogSearchVO) {
        String url = pubConfig.getDynamicServer() + "/sys/log/index.htm?";
        if (sysLogSearchVO.getUserId() != null)
            url += "&userId=" + sysLogSearchVO.getUserId();
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getStartDate()))
            url += "&startDate=" + sysLogSearchVO.getStartDate();
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getEndDate()))
            url += "&endDate=" + sysLogSearchVO.getEndDate();
        return url;
    }

}

package com.critc.plat.sys.controller;

import com.critc.plat.core.pub.PubConfig;
import com.critc.plat.sys.model.SysResource;
import com.critc.plat.sys.model.SysRole;
import com.critc.plat.sys.service.SysResourceService;
import com.critc.plat.sys.service.SysRoleService;
import com.critc.plat.util.string.BackUrlUtil;
import com.critc.plat.util.string.StringUtil;
import com.critc.plat.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统角色处理Controller
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@RequestMapping("/sys/role")
@Controller
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private PubConfig pubConfig;
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 进入角色维护界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/sys/role/index");
        List<SysRole> list = sysRoleService.list();
        mv.addObject("list", list);
        String url = pubConfig.getDynamicServer() + "/sys/role/index.htm?";
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }

    /**
     * 新增角色
     *
     * @param request
     * @param response
     * @param sysRole
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/sys/role/add");
        List<SysResource> listResource = sysResourceService.list();//所有菜单
        mv.addObject("listModule", listResource);
        List<SysResource> listFunction = sysResourceService.listByType(2);//所有功能按钮
        mv.addObject("listFunction", listFunction);
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 修改模块
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysRole sysRole = sysRoleService.get(id);
        mv.addObject("sysRole", sysRole);
        mv.setViewName("/plat/sys/role/update");
        List<SysResource> listResource = sysResourceService.list();//所有模块
        mv.addObject("listModule", listResource);
        List<SysResource> listFunction = sysResourceService.listByType(2);//所有功能按钮
        mv.addObject("listFunction", listFunction);
        String checkButton = sysRoleService.checkResourceAndFunction(sysRole.getId());
        mv.addObject("checkButton", checkButton);
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增角色
     *
     * @param request
     * @param response
     * @param sysRole
     * @return
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
//        sysRole.setCreatedBy(SessionUtil.getRealname(request));//创建人
        String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
        String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
        int flag = sysRoleService.add(sysRole, moduleArr, functionArr);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("角色新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("角色新增成功");
    }

    /**
     * 修改角色
     *
     * @param request
     * @param response
     * @param sysRole
     * @return
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
       // sysRole.setLastModifiedBy(SessionUtil.getRealname(request));//修改人
        String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
        String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
        int flag = sysRoleService.update(sysRole, moduleArr, functionArr);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("角色修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("角色修改成功");
    }

    /**
     * 删除角色
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysRoleService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("角色删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("角色删除成功");
    }
}

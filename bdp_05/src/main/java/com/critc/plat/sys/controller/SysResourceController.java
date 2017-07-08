package com.critc.plat.sys.controller;

import com.critc.plat.core.pub.PubConfig;
import com.critc.plat.sys.model.SysResource;
import com.critc.plat.sys.service.SysResourceService;
import com.critc.plat.util.string.BackUrlUtil;
import com.critc.plat.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统资源管理Controller
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Controller
@RequestMapping("/sys/resource")
public class SysResourceController {
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入资源维护界面
     * 显示所有模块列表，treeGrid显示
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/sys/resource/index");
        List<SysResource> list = sysResourceService.list();
        mv.addObject("list", list);// 把获取的记录放到mv里面
        String url = pubConfig.getDynamicServer() + "/sys/resource/index.htm?";
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }

    /**
     * 新增模块
     *
     * @param request
     * @param response
     * @param sysResource
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, SysResource sysResource) {
        ModelAndView mv = new ModelAndView();
        String ztree = sysResourceService.createZtreeByModule();//模块列表
        mv.addObject("ztree", ztree);
        if (sysResource.getParentId() != null && sysResource.getParentId() != 0) {
            SysResource parent = sysResourceService.get(sysResource.getParentId());
            sysResource.setParentName(parent.getName());
            sysResource.setType(2);//设置为增加功能界面
        } else {
            sysResource.setType(1);
        }
        mv.addObject("sysResource", sysResource);
        mv.setViewName("/plat/sys/resource/add");
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
        SysResource sysResource = sysResourceService.get(id);
        String ztree = sysResourceService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.addObject("sysResource", sysResource);
        mv.setViewName("/plat/sys/resource/update");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, SysResource sysResource) {
        if (sysResource.getParentId() == null)
            sysResource.setParentId(1);
        int flag = sysResourceService.add(sysResource);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("资源新增失败");
        else if (flag == 2)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("资源代码已存在");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("资源新增成功");
    }

    /**
     * 修改模块
     *
     * @param request
     * @param response
     * @param sysResource
     * @return
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, SysResource sysResource) {
        if (sysResource.getParentId() == null)
            sysResource.setParentId(1);
        if (sysResource.getId() == sysResource.getParentId()) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("不能和上级节点一样");
        } else {
            int flag = sysResourceService.update(sysResource);
            if (flag == 0)
                return "forward:/error.htm?msg=" + StringUtil.encodeUrl("资源修改失败");
            else if (flag == 2)
                return "forward:/error.htm?msg=" + StringUtil.encodeUrl("上级节点不存在");
            else
                return "forward:/success.htm?msg=" + StringUtil.encodeUrl("资源修改成功");
        }
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysResourceService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("删除失败");
        else if (flag == 2)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("还有下级节点，不能删除");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("删除成功");
    }


    /**
     * 进入功能维护列表
     *
     * @return
     */
    @RequestMapping("/functionIndex")
    public ModelAndView functionIndex(HttpServletRequest request, int parentId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/sys/resource/functionIndex");
        List<SysResource> list = sysResourceService.listByParentId(parentId);
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.addObject("resource", sysResourceService.get(parentId));//取得父节点放入mv
        String url = pubConfig.getDynamicServer() + "/sys/resource/functionIndex.htm?parentId=" + parentId;
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }


}

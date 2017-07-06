package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.SysResourceDao;
import com.critc.plat.sys.dao.SysRoleDao;
import com.critc.plat.sys.dao.SysRoleresourceDao;
import com.critc.plat.sys.model.SysRole;
import com.critc.plat.sys.model.SysRoleResource;
import com.critc.plat.util.model.ComboboxVO;
import com.critc.plat.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 系统角色处理Service
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysResourceDao sysResourceDao;
    @Autowired
    private SysRoleresourceDao sysRoleresourceDao;

    /**
     * 新增角色，同时新增对应的权限
     *
     * @param sysRole
     * @param moduleArr
     * @param functionArr
     * @return
     */
    public int add(SysRole sysRole, String moduleArr, String functionArr) {
        int roleId = sysRoleDao.add(sysRole);
        sysRoleresourceDao.deleteRoleResource(roleId);
        String[] moduleSplit = moduleArr.split("@@");
        for (int i = 0; i < moduleSplit.length; i++) {
            if (StringUtil.isNotNullOrEmpty(moduleSplit[i])) {
                sysRoleresourceDao.addRoleResource(roleId, Integer.parseInt(moduleSplit[i]));
            }
        }
        String[] functionSplit = functionArr.split("@@");
        for (int i = 0; i < functionSplit.length; i++) {
            if (StringUtil.isNotNullOrEmpty(functionSplit[i])) {
                sysRoleresourceDao.addRoleResource(roleId, Integer.parseInt(functionSplit[i]));
            }
        }
        return 1;
    }

    /**
     * 修改角色，同时新增对应的权限
     *
     * @param sysRole
     * @param moduleArr
     * @param functionArr
     * @return
     */
    public int update(SysRole sysRole, String moduleArr, String functionArr) {
        sysRoleDao.update(sysRole);
        sysRoleresourceDao.deleteRoleResource(sysRole.getId());
        String[] moduleSplit = moduleArr.split("@@");
        for (int i = 0; i < moduleSplit.length; i++) {
            if (StringUtil.isNotNullOrEmpty(moduleSplit[i])) {
                sysRoleresourceDao.addRoleResource(sysRole.getId(), Integer.parseInt(moduleSplit[i]));
            }
        }
        String[] functionSplit = functionArr.split("@@");
        for (int i = 0; i < functionSplit.length; i++) {
            if (StringUtil.isNotNullOrEmpty(functionSplit[i])) {
                sysRoleresourceDao.addRoleResource(sysRole.getId(), Integer.parseInt(functionSplit[i]));
            }
        }
        return 1;
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        int flag = 0;
        flag = sysRoleDao.delete(id);
        if (flag == 1) {
            sysRoleresourceDao.deleteRoleResource(id);
        }
        return flag;
    }

    /**
     * 通过js来设置选中的模块和按钮
     *
     * @param roleId
     * @return
     */
    public String checkResourceAndFunction(int roleId) {
        List<SysRoleResource> listRoleResource = sysRoleresourceDao.listRoleResourceByType(roleId, 1);// 角色模块列表
        List<SysRoleResource> listRoleFunction = sysRoleresourceDao.listRoleResourceByType(roleId, 2);// 角色对应功能
        StringBuilder sb = new StringBuilder();
        for (SysRoleResource sysRoleResource : listRoleResource) {
            sb.append("$('#mod_" + sysRoleResource.getResourceId() + "').prop('checked',true);\r\n");
        }
        for (SysRoleResource sysRoleFunction : listRoleFunction) {
            sb.append("$('#function_" + sysRoleFunction.getResourceId() + "').prop('checked',true);\r\n");
        }
        return sb.toString();
    }

    public SysRole get(int id) {
        return sysRoleDao.get(id);
    }

    public List<SysRole> list() {
        return sysRoleDao.list();
    }

    public List<ComboboxVO> listCombo() {
        return sysRoleDao.listCombo();
    }

    /**
     * 根据roleId来获取该角色具有的功能按钮
     *
     * @param roleId
     * @return
     */
    public HashMap<String, String> getRoleFunctions(int roleId) {
        HashMap<String, String> hashFunctions = null;
        if (hashFunctions == null) {
            hashFunctions = new HashMap<>();
            List<SysRoleResource> listRoleResource = sysRoleresourceDao.listRoleResourceByType(roleId, 2);
            for (SysRoleResource sysRoleResource : listRoleResource) {
                hashFunctions.put(sysRoleResource.getResourceCode(), sysRoleResource.getUrl());
            }
        }
        return hashFunctions;
    }

    /**
     * 根据roleId来获取所有的资源，返回hashmap，key为url，value为0/1,0不具备该权限，1具备
     *
     * @param roleId
     * @return
     */
    public HashMap<String, Integer> getRoleResources(int roleId) {
        HashMap<String, Integer> hashRoleResources = null;
        if (hashRoleResources == null) {
            hashRoleResources = new HashMap<>();
            List<SysRoleResource> listRoleResource = sysRoleresourceDao.listRoleResource(roleId);
            for (SysRoleResource sysRoleResource : listRoleResource) {
                hashRoleResources.put(sysRoleResource.getUrl(), sysRoleResource.getResourceId() == 0 ? 0 : 1);
            }
        }
        return hashRoleResources;
    }
}

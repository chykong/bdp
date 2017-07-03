package com.critc.plat.sys.dao;

import com.critc.plat.core.dao.BaseDao;
import com.critc.plat.sys.model.SysResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统资源管理Dao
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Repository
public class SysResourceDao extends BaseDao<SysResource, SysResource> {

    /**
     * 新增
     *
     * @param sysResource
     * @return
     */
    public int add(SysResource sysResource) {
        String sql = "insert into t_sys_resource(id,name,code,parent_id,url,target,iconImg,display_order,type,description)" +
                " values(seq_t_sys_resource.nextval,:name,:code,:parentId,:url,:target,:iconImg,:displayOrder,:type,:description)";
        return insert(sql, sysResource);
    }

    /**
     * 修改
     *
     * @param sysResource
     * @return
     */
    public int update(SysResource sysResource) {
        String sql = "update t_sys_resource set name=:name,code=:code,url=:url,parent_id=:parentId,target=:target,iconImg=:iconImg," +
                "display_order=:displayOrder,type=:type,description=:description where id=:id";
        return update(sql, sysResource);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_sys_resource where id=?";
        return delete(sql, id);
    }

    /**
     * 按上级id删除，删除对应的功能
     *
     * @param id
     * @return
     */
    public int deleteByParentId(int id) {
        String sql = "delete from t_sys_resource where parent_id=?";
        return delete(sql, id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public SysResource get(int id) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,(select name from t_sys_resource where id=t.parent_id) parent_name from t_sys_resource t where id=?";
        return get(sql, id);
    }

    /**
     * 获取所有资源
     *
     * @return
     */
    public List<SysResource> list() {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,(select count(*) from t_sys_resource where parent_id=t.id) cnt,(select name from t_sys_resource where id=t.parent_id)parentName" +
                " from t_sys_resource t order by parent_id, display_order";
        return list(sql);
    }

    /**
     * 获取所有资源
     *
     * @return
     */
    public List<SysResource> listByType(int type) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,(select count(*) from t_sys_resource where parent_id=t.id) cnt from t_sys_resource t where type =? order by parent_id, display_order";
        return list(sql, type);
    }

    /**
     * 获取下级节点总数
     *
     * @param id
     * @return
     */
    public int getChildCount(int id) {
        String sql = "select count(*) from t_sys_resource where parent_id=? and type=1";
        return count(sql, id);
    }

    /**
     * 根据parentId获取下面的所有功能
     *
     * @param parentId
     * @return
     */
    public List<SysResource> listByParentId(int parentId) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description,(select count(*) from t_sys_resource where parent_id=t.id) cnt from t_sys_resource t where parent_id=? order by display_order";
        return list(sql, parentId);
    }

    /**
     * s
     * 根据角色id获取模块
     *
     * @param roleId
     * @return
     */
    public List<SysResource> listByRoleId(int roleId) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description from t_sys_resource ";
        sql += " where id in (select  module_id from t_sys_rolemodule where role_id =?)";
        sql += " order by parent_id, display_order";
        return list(sql, roleId);
    }

    /**
     * 根据模块代码获取模块信息
     *
     * @param code
     * @return
     */
    public SysResource getByModuleCode(String code) {
        String sql = "select t.id,t.name,t.code,t.parent_id,t.url,t.target,t.iconimg,t.display_order,t.type,t.description from t_sys_resource t where code=?";
        return get(sql, code);
    }


}

package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.SysResourceDao;
import com.critc.plat.sys.dao.SysUserDao;
import com.critc.plat.sys.model.SysUser;
import com.critc.plat.sys.vo.SysUserSearchVO;
import com.critc.plat.util.code.RandomCodeUtil;
import com.critc.plat.util.encrypt.Md5SaltUtil;
import com.critc.plat.util.model.ComboboxVO;
import com.critc.plat.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户管理Service
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysResourceDao sysResourceDao;

    /**
     * 用户新增，先判断用户名是否存在 返回2，账号已存在，返回1操作成功
     *
     * @param sysUser
     * @return
     */
    public int add(SysUser sysUser) {
        int flag = 0;
        SysUser exist = sysUserDao.getByUsername(sysUser.getUsername());
        if (exist != null)
            flag = 2;
        else {
            // 设置密码
            String password = "123456";
            String randomcode = RandomCodeUtil.createRandomCode(6);
            Md5SaltUtil md5SaltUtil = new Md5SaltUtil(randomcode);
            String md5Pass = md5SaltUtil.encode(password);
            sysUser.setPassword(md5Pass);
            sysUser.setRandomcode(randomcode);
            flag = sysUserDao.add(sysUser);
            flag = 1;
        }
        return flag;
    }

    public int update(SysUser sysUser) {
        int flag = 0;
        flag = sysUserDao.update(sysUser);
        return flag;
    }

    public int delete(int id) {
        return sysUserDao.delete(id);
    }

    public SysUser get(int id) {
        return sysUserDao.get(id);
    }

    /**
     * 根据username获取用户
     *
     * @param username
     * @return
     */
    public SysUser getByUsername(String username) {
        return sysUserDao.getByUsername(username);
    }

    /**
     * 用户列表
     *
     * @param sysUserSearchVO
     * @return
     */
    public List<SysUser> list(SysUserSearchVO sysUserSearchVO) {
        List<SysUser> list = sysUserDao.list(sysUserSearchVO);
        return list;
    }

    /**
     * 用户列表总数
     *
     * @param sysUserSearchVO
     * @return
     */
    public int count(SysUserSearchVO sysUserSearchVO) {
        return sysUserDao.count(sysUserSearchVO);
    }


    /**
     * 修改密码
     *
     * @param id
     * @param oldPass
     * @param newPass
     * @return
     */
    public int updatePass(int id, String oldPass, String newPass) {
        int flag = 0;
        SysUser getUser = sysUserDao.get(id);
        // 判断原密码是否为空，不为空则修改新密码
        if (StringUtil.isNotNullOrEmpty(oldPass)) {
            Md5SaltUtil md5SaltUtil = new Md5SaltUtil(getUser.getRandomcode());
            if (md5SaltUtil.isPasswordValid(getUser.getPassword(), oldPass)) {
                String newRandomcode = RandomCodeUtil.createRandomCode(6);
                Md5SaltUtil md5SaltUtil12 = new Md5SaltUtil(newRandomcode);
                String md5Pass = md5SaltUtil12.encode(newPass);
                sysUserDao.updatePass(getUser.getId(), md5Pass, newRandomcode);
                flag = 1;
            } else {
                flag = 2;
            }
        }
        return flag;
    }

    /**
     * 校验密码是否正确
     *
     * @param sysUser
     * @param password
     * @return
     */
    public boolean checkPass(SysUser sysUser, String password) {
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(sysUser.getRandomcode());
        return md5SaltUtil.isPasswordValid(sysUser.getPassword(), password);
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    public int saveResetPass(int id) {
        int flag = 0;
        String password = "123456";
        String randomcode = RandomCodeUtil.createRandomCode(6);
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(randomcode);
        String md5Pass = md5SaltUtil.encode(password);
        flag = sysUserDao.updatePass(id, md5Pass, randomcode);
        return flag;
    }

    /**
     * 修改状态，锁定解锁用户时使用
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(int id, int status) {
        return sysUserDao.updateStatus(id, status);
    }

    /**
     * 所有人员列表，查询日志使用
     *
     * @return
     */
    public List<ComboboxVO> listAllUser() {
        return sysUserDao.listAllUser();
    }

    /**
     * 用户列表
     *
     * @return
     */
    public List<SysUser> listAll() {
        List<SysUser> list = sysUserDao.listAll();
        return list;
    }
}

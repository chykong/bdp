package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.SysLogDao;
import com.critc.plat.sys.model.SysLog;
import com.critc.plat.sys.vo.SysLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统日志service
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Service
public class SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 日志列表
     *
     * @param sysLogSearchVO
     * @return
     */
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
        List<SysLog> list = sysLogDao.list(sysLogSearchVO);
        return list;
    }

    /**
     * 日志列表
     *
     * @param sysLogSearchVO
     * @return
     */
    public List<SysLog> listAll(SysLogSearchVO sysLogSearchVO) {
        List<SysLog> list = sysLogDao.listAll(sysLogSearchVO);
        return list;
    }

    /**
     * 日志列表总数
     *
     * @param sysLogSearchVO
     * @return
     */
    public int count(SysLogSearchVO sysLogSearchVO) {
        return sysLogDao.count(sysLogSearchVO);
    }

}

package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.SysLogDao;
import com.critc.plat.sys.model.SysLog;
import com.critc.plat.sys.model.SysResource;
import com.critc.plat.sys.vo.SysLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 系统日志service
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Service
@Configuration
@EnableAsync
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


    /**
     * 操作时记录日志
     */
    @Async
    public void addLog(int userId, String url, String parameters, String operaIp) {
        HashMap<String, SysResource> hashMap = sysResourceService.getAllResource();//获取所有资源

        SysLog sysLog = new SysLog();
        sysLog.setUserId(userId);
        sysLog.setOperaUrl(url);
        if (parameters.length() > 500)
            parameters = parameters.substring(0, 500);
        sysLog.setOperaParams(parameters);
        sysLog.setOperaDate(new Date());
        sysLog.setOperaIp(operaIp);

        SysResource sysResource = hashMap.get(url);
        if (sysResource != null) {
            sysLog.setModuleName(sysResource.getParentName());
            sysLog.setOperaName(sysResource.getName());
        }
        sysLogDao.add(sysLog);
    }

}

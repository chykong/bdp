package com.critc.plat.util.page;

import com.critc.plat.util.global.GlobalConst;

/**
 * 分页工具类，用于生成分页语句
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class PageUtil {

    /**
     * 生成mysql分页查询语句
     *
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String createMysqlPageSql(String sql, int pageIndex, int pageSize) {
        return sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
    }

    /**
     * 生成mysql分页查询语句
     *
     * @param sql
     * @param pageIndex
     * @return
     */
    public static String createMysqlPageSql(String sql, int pageIndex) {
        return sql += " limit " + (pageIndex - 1) * GlobalConst.PAGESIZE + "," + GlobalConst.PAGESIZE;
    }

}

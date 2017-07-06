package com.critc.plat.util.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字符串操作，用于保存和Web输入输出有关的方法
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class WebUtil {

    /**
     * 在controller或action里面打印字符串，返回给前台
     *
     * @param response
     * @param str
     */
    public static void out(HttpServletResponse response, String str) {
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据字符串转换，如果为null，则变成""
     *
     * @param obj
     * @return
     */
    public static String getSafeStr(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultStr
     *
     * @param obj
     * @param strDefault 为空默认值
     * @return
     */
    public static String getSafeStr(Object obj, String strDefault) {
        return obj == null ? strDefault : String.valueOf(obj);
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param obj
     * @return
     */
    public static int getSafeInt(Object obj) {
        return obj == null || obj.toString().equals("") ? 0 : Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultInt
     *
     * @param obj
     * @param nDefualt 为空的默认值
     * @return
     */
    public static int getSafeInt(Object obj, int nDefualt) {
        return obj == null || obj.toString().equals("") ? nDefualt : Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param obj
     * @return
     */
    public static double getSafeDouble(Object obj) {
        return obj == null ? 0 : Double.parseDouble(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultDouble
     *
     * @param obj
     * @param nDefualt
     * @return
     */
    public static double getSafeDouble(Object obj, double nDefualt) {
        return obj == null ? 0 : Double.parseDouble(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param obj
     * @return
     */
    public static float getSafeFloat(Object obj) {
        return obj == null ? 0 : Float.parseFloat(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultDouble
     *
     * @param obj
     * @return
     */
    public static float getSafeFloat(Object obj, float nDefualt) {
        return obj == null ? 0 : Float.parseFloat(String.valueOf(obj));
    }

}

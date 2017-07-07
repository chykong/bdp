package com.critc.plat.util.file;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件下载工具类
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class DownloadUtil {

    /**
     * 文件下载方法
     *
     * @param response
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    public static void download(HttpServletResponse response, String filePath, String fileName, String encode) {
        response.setContentType("text/html;charset=" + encode);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String downLoadPath = filePath;
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);// new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 文件下载
     * @param response
     * @param filePath
     * @param fileName
     */
    public static void download(HttpServletResponse response, String filePath, String fileName) {
        DownloadUtil.download(response, filePath, fileName, "UTF-8");
    }
}

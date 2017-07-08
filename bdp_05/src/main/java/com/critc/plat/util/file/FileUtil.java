package com.critc.plat.util.file;

import java.io.*;

/**
 * 文件处理工具类
 *
 * @author 孔垂云
 * @date 2017年2月7日
 */
public class FileUtil {
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param filePath 文件名，路径加文件名
     * @author 孔垂云
     * @date 2017年2月7日
     */
    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder("");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String tempString = null;
            //			int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString).append("\r\n");
                //				line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }
        return sb.toString();
    }

    /**
     * 写文件
     *
     * @param filePath    文件全路径
     * @param fileContent 文件内容
     * @author 孔垂云
     * @date 2017年2月7日
     */
    public static int writeFile(String filePath, String fileContent) {
        int flag = 0;
        try {
            String path = filePath.substring(0, filePath.lastIndexOf("\\\\"));
            File fileDir = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(fileContent);
            writer.close();
            flag = 1;
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            System.out.println(filePath);
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新建文件夹
     *
     * @param path
     */
    public static void createFileDir(String path) {
        File fileDir = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

    }

    /**
     * 删除文件
     *
     * @param path_name 路径
     */
    public static void delete(String path_name) {
        File dest = new File(path_name);
        if (dest.exists())
            dest.delete();
    }
}

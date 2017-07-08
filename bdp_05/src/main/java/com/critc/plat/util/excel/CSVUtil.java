package com.critc.plat.util.excel;

import java.io.*;

/**
 * 导出CSV文件
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class CSVUtil {

    /**
     * 生成为CVS文件
     *
     * @param outPutPath
     * @param fileName   要导出生成的文件名
     * @param data       二维数组
     * @return
     */
    public static File createCSVFile(String outPutPath, String fileName, String[][] data) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdir();
            }
            //定义文件名格式并创建
            csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    csvFileOutputStream.write(data[i][j]);
                    if (j != data[i].length - 1)
                        csvFileOutputStream.write(",");
                }
                csvFileOutputStream.newLine();
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }


    /**
     * 测试数据
     *
     * @param args
     */
    public static void main(String[] args) {

        String[][] data = new String[2][2];
        data[0][0] = "序号";
        data[0][1] = "内容";

        data[1][0] = "1";
        data[1][1] = "测试";

        String path = "c:/export/";
        String fileName = "文件导出";
        File file = CSVUtil.createCSVFile(path, fileName, data);
        String fileName2 = file.getName();
        System.out.println("文件名称：" + fileName2);
    }
}

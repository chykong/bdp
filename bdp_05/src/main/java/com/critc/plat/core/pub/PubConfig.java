package com.critc.plat.core.pub;

import org.springframework.stereotype.Component;

/**
 * 全局参数配置，用于在后端获取参数
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Component
public class PubConfig {
    private String imageServer;//图片显示服务器地址
    private String imageUploadPath;//图片路径
    private String staticServer;//静态地址
    private String dynamicServer;//动态地址

    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }

    public String getImageUploadPath() {
        return imageUploadPath;
    }

    public void setImageUploadPath(String imageUploadPath) {
        this.imageUploadPath = imageUploadPath;
    }

    public String getStaticServer() {
        return staticServer;
    }

    public void setStaticServer(String staticServer) {
        this.staticServer = staticServer;
    }

    public String getDynamicServer() {
        return dynamicServer;
    }

    public void setDynamicServer(String dynamicServer) {
        this.dynamicServer = dynamicServer;
    }

    @Override
    public String toString() {
        return "PubConfig{" +
                "imageServer='" + imageServer + '\'' +
                ", imageUploadPath='" + imageUploadPath + '\'' +
                ", staticServer='" + staticServer + '\'' +
                ", dynamicServer='" + dynamicServer + '\'' +
                '}';
    }
}

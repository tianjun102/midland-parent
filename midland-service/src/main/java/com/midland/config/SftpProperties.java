package com.midland.config;

import com.midland.core.util.AppSetting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Properties;

@Configuration
public class SftpProperties {
    private static SftpProperties sftpProperties;

    private static Properties prop = new Properties();
    private String host=String.valueOf(prop.get("sftp.host"));//服务器连接ip
    private String username=String.valueOf(prop.get("sftp.username"));//用户名
    private String password=String.valueOf(prop.get("sftp.password"));//密码
    private int port=Integer.valueOf(String.valueOf(prop.get("sftp.port")));//端口号
    private String privateKey=String.valueOf(prop.get("sftp.privateKey"));//私钥
    private String basePath=String.valueOf(prop.get("sftp.basePath"));//根目录


    private String imgDomain=String.valueOf(prop.get("img.domain"));//图片根地址
    private String filePath=String.valueOf(prop.get("file.path"));//图片根地址
    private String videoPath=String.valueOf(prop.get("video.path"));//图片根地址
    private String imgPath=String.valueOf(prop.get("img.path"));//图片根地址

    static{
        try {
            InputStream inputStream =
                    AppSetting.class.getClassLoader().getResourceAsStream("/properties/sftp.properties");
            prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static SftpProperties getInstance(){
        if (sftpProperties==null){
            synchronized (SftpProperties.class){
                if (sftpProperties==null){
                    sftpProperties=new SftpProperties();
                }
            }
        }
        return sftpProperties;
    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}

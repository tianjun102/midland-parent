package com.midland.config;

import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SftpProperties {

    @Value("${sftp.host}")
    private String host;//服务器连接ip
    @Value("${sftp.username}")
    private String username;//用户名
    @Value("${sftp.password}")
    private String password;//密码
    @Value("${sftp.port}")
    private int port;//端口号
    @Value("${sftp.privateKey}")
    private String privateKey;//私钥
    @Value("${sftp.basePath}")
    private String basePath;//根目录


    @Value("${img.domain}")
    private String imgDomain;//图片根地址
    @Value("${file.path}")
    private String filePath;//图片根地址
    @Value("${video.path}")
    private String videoPath;//图片根地址
    @Value("${img.path}")
    private String imgPath;//图片根地址


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

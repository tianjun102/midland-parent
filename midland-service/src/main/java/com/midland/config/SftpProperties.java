package com.midland.config;

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

package com.midland.configuration;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.midland.config.SftpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
@Component
public class SftpConfiguration {

    @Autowired
    private SftpProperties sftpProperties;

    @Bean
    public ChannelSftp SftpClient (){
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();

            Session sshSession = jsch.getSession(sftpProperties.getUsername(), sftpProperties.getHost(), sftpProperties.getPort());
            System.out.println("Session created.");
            sshSession.setPassword(sftpProperties.getPassword());
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + sftpProperties.getHost() + ".");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sftp;
    }

}

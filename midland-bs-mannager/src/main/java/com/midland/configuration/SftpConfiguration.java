package com.midland.configuration;

import com.jcraft.jsch.*;
import com.midland.config.SftpProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
@Component
public class SftpConfiguration {
    private Logger logger= LoggerFactory.getLogger(SftpConfiguration.class);

    @Autowired
    private SftpProperties sftpProperties;

    @Bean
    public ChannelSftp SftpClient (){
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();

            Session sshSession = jsch.getSession(sftpProperties.getUsername(), sftpProperties.getHost(), sftpProperties.getPort());
            logger.info("Session created.");
            sshSession.setPassword(sftpProperties.getPassword());
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.info("Session connected.");
            logger.info("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("Connected to " + sftpProperties.getHost() + ".");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sftp;
    }

}

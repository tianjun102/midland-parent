package com.midland.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Configuration
public class PublicKeyConfiguration {

    public static Logger logger = LoggerFactory.getLogger(PublicKeyConfiguration.class);
    @Bean
    public PublicKey getPubKey(){
        CertificateFactory cf;
        InputStream in2;
        java.security.cert.Certificate c2 = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            in2 = this.getClass().getResourceAsStream("/siam.pem");
            System.out.println("读取证书文件成功");
            c2 = cf.generateCertificate(in2);
        } catch (CertificateException e) {
            logger.error("getPubKey ",e);
        }
        return c2.getPublicKey();
    }
}

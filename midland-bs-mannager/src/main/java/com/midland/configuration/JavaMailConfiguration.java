package com.midland.configuration;

import com.midland.web.api.mailSender.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
@Component
public class JavaMailConfiguration {
	@Autowired
	private MailProperties mailProperties;
	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl javaMailSender =new JavaMailSenderImpl() ;
		Properties props = new Properties();
		props.setProperty("mail.smtp.socketFactory.class", mailProperties.getSslFactory());
		props.setProperty("mail.smtp.socketFactory.fallback", mailProperties.getFallback());
		props.setProperty("mail.smtp.port", mailProperties.getPort());
		props.setProperty("mail.smtp.socketFactory.port", mailProperties.getFactoryPort());
		props.setProperty("mail.smtp.auth", mailProperties.getAuth());
		props.put("mail.smtp.host",mailProperties.getHost());
		props.put("mail.smtp.username", mailProperties.getUserName());
		props.put("mail.smtp.password", mailProperties.getAuthCode());
		Session session = Session.getDefaultInstance(props,  new Authenticator() {
			//身份认证
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailProperties.getUserName(), mailProperties.getAuthCode());
			}
		});
		javaMailSender.setSession(session);
		return javaMailSender;
	}
	
}

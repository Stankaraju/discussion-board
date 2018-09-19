package com.discussionboard.webconfig;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:/application.properties")
public class MailConfig {
	
	@Value("${mail.host}")
	private String host;
	
	@Value("${mail.subject}")
	private String subject;
	
	@Value("${mail.port}")
	private String port;
	
//	@Value("${mail.from}")
//	private String from;
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;
	

	@Bean
	public JavaMailSender jMSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setPort(Integer.parseInt(port));
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		
		  Properties props = javaMailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	 
		
	return javaMailSender;		
	}
	
}

package com.discussionboard.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleMailController {

	@Autowired
	private JavaMailSender mailSender;
	

	
	@RequestMapping("/sendMail")
	public @ResponseBody String sendSimpleMessage(
		      String to, String subject, String text) {
		        
		        sendMail("","");
		        return "email sent";
		        
	}

	public void sendMail(String toAddress, String sub) {
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setTo(toAddress); 
		message.setSubject(sub); 
		message.setText("welcome to discussion board");
		mailSender.send(message);
	}

	}


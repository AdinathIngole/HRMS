package com.HRMS.ServiceImpl;

import java.io.File;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.HRMS.Exception.UserException;
import com.HRMS.Service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendEmail(String to, String subject, String body) {
		 SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        mailSender.send(message);
		
	}

	@Override
	public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			 	helper.setTo(to);
	            helper.setSubject(subject);
	            helper.setText(body);
	            
	            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
	            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
	            mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new UserException("Failed to send email with attachment");
		}
		
	}

}

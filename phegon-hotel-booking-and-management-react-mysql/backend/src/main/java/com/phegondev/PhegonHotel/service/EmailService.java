package com.phegondev.PhegonHotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service("emailService")
public class EmailService {

	
	private JavaMailSender mailSender;
	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}
	
    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        // Create a MimeMessage object
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // Use MimeMessageHelper to configure the message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);  // 'true' indicates HTML content

        // Send the email
        mailSender.send(mimeMessage);
    }
}

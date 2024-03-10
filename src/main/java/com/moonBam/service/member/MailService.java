package com.moonBam.service.member;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

    //이메일 전송 함수(수정 X)(람다식)
    public void sendEmail(String from, String to, String subject, String body) throws Exception {
        javaMailSender.send(
        	(MimeMessagePreparator) mimeMessage -> {
	        	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);				// true: 멀티파트 메세지를 사용
	            messageHelper.setFrom(from);
	            messageHelper.setTo(to);
	            messageHelper.setSubject(subject);
	            messageHelper.setText(body, true);														// true: html을 사용
        });
    }

    //이메일 전송 함수(수정 X)(일반식)
//    private void sendEmail(String from, String to, String subject, String body) throws Exception {
//	    javaMailSender.send(new MimeMessagePreparator() {
//
//	    	@Override
//	        public void prepare(MimeMessage mimeMessage) throws Exception {
//	            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);	    		// true: 멀티파트 메세지를 사용
//	            messageHelper.setFrom(from);
//	            messageHelper.setTo(to);
//	            messageHelper.setSubject(subject);
//	            messageHelper.setText(body, true);														// true: html을 사용
//	        }
//		});
//	}
    
    //이메일과 파일 전송 함수(수정 X)(일반식)
    public void sendEmailWithFiles(String from, String to, String subject, String body) throws Exception {
    	javaMailSender.send(new MimeMessagePreparator() {

	    	@Override
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	    		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
	            messageHelper.setFrom(from);
	            messageHelper.setTo(to);
	            messageHelper.setSubject(subject);
	            messageHelper.setText(body, true);
	
	            //로컬 데이터만 전송됨............
	        	FileSystemResource file = new FileSystemResource(new File("resources/images/sample.jpg")); 
	        	messageHelper.addAttachment("resources/images/sample.jpg", file);
	        }
		});
	}  
    
    

	
	
}
package com.moonBam.service.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

    //이메일 전송 함수(수정 X)(람다식)
    public void sendEmail(String from, String to, String subject, String body, String innerImageName, String innerImagePath) throws Exception {
        javaMailSender.send(
        	(MimeMessagePreparator) mimeMessage -> {
	        	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);				// true: 멀티파트 메세지를 사용
	            messageHelper.setFrom(from);
	            messageHelper.setTo(to);
	            messageHelper.setSubject(subject);
	            messageHelper.setText(body, true);														// true: html을 사용
	            messageHelper.addInline(innerImageName, new ClassPathResource(innerImagePath));
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
    public void sendEmailWithFiles(String from, String to, String subject, String body, String filePath, String fileName, String innerImageName, String innerImagePath) throws Exception {
    	javaMailSender.send(new MimeMessagePreparator() {

	    	@Override
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	    		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
	            messageHelper.setFrom(from);
	            messageHelper.setTo(to);
	            messageHelper.setSubject(subject);
	            messageHelper.setText(body, true);
	        	messageHelper.addAttachment(fileName, new ClassPathResource(filePath));
	        	messageHelper.addInline(innerImageName, new ClassPathResource(innerImagePath));
	        }
		});
	}  
    
public String EmailBody(String emailPath, Map<String, String> changeData) throws IOException {
    	
    	//Resources 폴더부터 경로 설정
		ClassPathResource resource = new ClassPathResource(emailPath);
		//BufferedReader를 통해 한줄씩 읽어옴 || InputStreamReader를 통해 byte를 String Stream으로 변경
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		//StringBuilder: 문자열 연산을 수행할 때마다, 기존 문자열에 변경사항을 반영하여 작업
		StringBuilder emailBody = new StringBuilder();

		String line;
		//HTML의 각 줄을 읽어오고, 특정 글자는 치환
		while ((line = br.readLine()) != null) {
			for (Map.Entry<String, String> entry : changeData.entrySet()) {
			    line = line.replace(entry.getKey(), entry.getValue());
			}
		    //각 줄을 StringBuilder에 더하고, 개행
		    emailBody.append(line).append("\n");
		}
    	String mesg = emailBody.toString();
		return mesg;
    }
    



	
	
}
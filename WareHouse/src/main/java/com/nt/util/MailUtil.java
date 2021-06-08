package com.nt.util;

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.nt.model.WhUserType;

@Component
public class MailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	/***
	 * This method is used to send email with below details 
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @return
	 */
	
	public boolean sendEmail(
			String to,
			String[] cc,
			String[] bcc,
			String subject,
			String text,
			MultipartFile file) {
		
		boolean flag=false;
		try {
			//1. create empty mail
			MimeMessage message=mailSender.createMimeMessage();
			
			//2. fill details
			MimeMessageHelper helper=new MimeMessageHelper(message,file!=null);
			helper.setTo(to);
			if(cc!=null && cc.length>0)
				helper.setCc(cc);
			
			if(bcc!=null && bcc.length>0)
				helper.setBcc(bcc);
			
			
			helper.setSubject(subject);
			helper.setText(text,true);
			
			//3. add attachment
			if(file!=null)
				helper.addAttachment(file.getOriginalFilename(),file);
			
			// 4. send email 
			mailSender.send(message);
			flag=true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return flag;
	}
	
	/***
	 * This method is used to send email with below details 
	 * @param to
	 * @param subject
	 * @param text
	 * @return
	 */
	public boolean sendEmail(
			String to,
			String subject,
			String text) 
	{
		return sendEmail(to, null, null, subject, text, null);
	}
	
	/***
	 * This method is used to send email with below details 
	 * @param to
	 * @param subject
	 * @param text
	 * @param file
	 * @return
	 */
	public boolean sendEmail(
			String to,
			String subject,
			String text,
			MultipartFile file
			) 
	{
		return sendEmail(to, null, null, subject, text, file);
	}
	
	public String getWhUserTemplateData(WhUserType whUserType) {
		try {
			//loading file
			ClassPathResource file = new ClassPathResource("whusermt.txt");
			//read data as Stream
			InputStream fis = file.getInputStream();
			//load file data into byte[]
			byte[] arr = new byte[fis.available()];
			fis.read(arr);
			// convert byte[] to String
			String text = new String(arr);
			
			text = text.replace("{{username}}", whUserType.getUserCode())
					.replace("{{usrtype}}", whUserType.getUserType())
					.replace("{{usrcontact}}", whUserType.getUserContact())
					;
			return text;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

package com.github.lxgang.utils.email.instance;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.lxgang.utils.email.beans.EmailObj;
import com.github.lxgang.utils.email.instance.SendEmail;
import com.github.lxgang.utils.email.instance.SendEmailFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SendEmailTest {
	private static Logger logger = LoggerFactory.getLogger(SendEmailTest.class);
	
	@Resource
	private SendEmailFactory sendEmailFactory;
	
	@Test
	public void sendTextEmail(){
		SendEmail sendEmail = sendEmailFactory.getInstance();
		
		String emailContent = "test send email<br> asodifhja";
		
		EmailObj obj = new EmailObj();
		
		obj.setEmailAddress("lxgang696@163.com,dada@asdf,liuxiaogang@gmf-inc.com");
		obj.setEmailTitle("Skype for business");
		obj.setEmailContent(emailContent);
		sendEmail.sendTextEmial(obj);
	}
	
	@Test
	public void sendHtmlEmail(){
		SendEmail sendEmail = sendEmailFactory.getInstance();
		
		String emailContent = "test send email2<br> asodifhja";
		
		EmailObj obj = new EmailObj();
		
		obj.setEmailAddress("lxgang696@163.com,dada@asdf,liuxiaogang@gmf-inc.com");
		obj.setEmailTitle("Skype for business");
		obj.setEmailContent(emailContent);
		sendEmail.sendHtmlEmail(obj);
	}
}

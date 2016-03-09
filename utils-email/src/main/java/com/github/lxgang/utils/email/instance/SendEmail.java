package com.github.lxgang.utils.email.instance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lxgang.utils.email.beans.EmailObj;
import com.github.lxgang.utils.email.beans.MailSenderInfo;

public class SendEmail {
	private Logger logger = LoggerFactory.getLogger(SendEmail.class);
	
	private String sever = "";
	private String port = "";
	private String username = "";
	private String password = "";
	private String nicename = "";
	
	SendEmail(String sever, String port, String username, String password, String nicename){
		this.sever = sever;
		this.port = port;
		this.username = username;
		this.password = password;
		this.nicename = nicename;
	}
	
	public void sendTextEmial(EmailObj emailObj){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(sever);
		mailInfo.setMailServerPort(port);
		mailInfo.setValidate(true);
		mailInfo.setUserName(username);
		mailInfo.setPassword(password);// 您的邮箱密码
		mailInfo.setFromAddress(username);
		mailInfo.setNice(nicename);
		mailInfo.setValidate(emailObj.isValidate());
		
		String emails = emailObj.getEmailAddress();
		String[] to = null;
		if(emails.indexOf(",") == -1){
			to = new String[] {emails };
		}else{
			to = emails.split(",");
		}
		mailInfo.setToAddress(to);
		mailInfo.setSubject(emailObj.getEmailTitle());
		mailInfo.setContent(emailObj.getEmailContent());
	    boolean flag = SimpleMailSender.sendTextMail(mailInfo);
	    
	    int count=0;
	    while(!flag){
	    	count++;
	    	flag = SimpleMailSender.sendTextMail(mailInfo);
	    	if(count > 3){
	    		break;
	    	}
	    }
	    if(count>3){
	    	logger.info("邮件发送失败");
		}else{
			logger.info("邮件发送成功");
		}
	}
	
	public void sendHtmlEmail(EmailObj emailObj){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(sever);
		mailInfo.setMailServerPort(port);
		mailInfo.setValidate(true);
		mailInfo.setUserName(username);
		mailInfo.setPassword(password);// 您的邮箱密码
		mailInfo.setFromAddress(username);
		mailInfo.setNice(nicename);
		mailInfo.setValidate(emailObj.isValidate());
		
		String emails = emailObj.getEmailAddress();
		String[] to = null;
		if(emails.indexOf(",") == -1){
			to = new String[] {emails };
		}else{
			to = emails.split(",");
		}
		mailInfo.setToAddress(to);
		mailInfo.setSubject(emailObj.getEmailTitle());
		mailInfo.setContent(emailObj.getEmailContent());
	    boolean flag = SimpleMailSender.sendHtmlMail(mailInfo);
	    
	    int count=0;
	    while(!flag){
	    	count++;
	    	flag = SimpleMailSender.sendHtmlMail(mailInfo);
	    	if(count > 3){
	    		break;
	    	}
	    }
	    if(count>3){
	    	logger.info("邮件发送失败");
		}else{
			logger.info("邮件发送成功");
		}
	}
}

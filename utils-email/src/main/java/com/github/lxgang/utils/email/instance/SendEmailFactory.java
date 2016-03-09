package com.github.lxgang.utils.email.instance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendEmailFactory {
	@Value("${com.github.email.server}")
	private String sever;
	
	@Value("${com.github.email.port}")
	private String port;
	
	@Value("${com.github.email.username}")
	private String username;
	
	@Value("${com.github.email.password}")
	private String password;
	
	@Value("${com.github.email.nicename}")
	private String nicename;
	
	private static SendEmail sendEmail; 
	
	public SendEmail getInstance() {
		if(sendEmail == null){
			sendEmail = new SendEmail(sever, port, username, password, nicename);
		}
		return sendEmail;
	}

}

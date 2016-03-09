package com.github.lxgang.utils.email.beans;

public class EmailObj {
	private String emailAddress;
	private boolean validate;
	private String emailTitle;
	private String emailContent;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String getEmailTitle() {
		return emailTitle;
	}
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	@Override
	public String toString() {
		return "EmailObj [emailAddress=" + emailAddress + ", validate=" + validate + ", emailTitle=" + emailTitle
				+ ", emailContent=" + emailContent + "]";
	}
}

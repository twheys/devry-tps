package edu.devry.cis470.tps.controller.dto;

public class RegisterForm {

	private String userName;
	private String password;
	private String passwordConfirm;
	private String emailAddress;
	private UserType userType;

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public String getUserName() {
		return userName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setPasswordConfirm(final String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public void setUserType(final UserType userType) {
		this.userType = userType;
	}
}

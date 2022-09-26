package com.cognixia.jump.model;

public class LoginForm
{
	public String UserName;
	public String Password;
	
	public LoginForm()
	{
		UserName = null;
		Password = null;
	}
	
	public LoginForm(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [UserName=" + UserName + ", Password=" + Password + "]";
	}
	
}

package com.cognixia.jump.model;

public class User
{
	public int UserId;
	public String UserName;
	public String Name;
	public String Password;
	public int Balance;
	public int AddressId;
	
	public User(int userId, String userName, String name, String password, int balance, int addressId)
	{
		super();
		UserId = userId;
		UserName = userName;
		Name = name;
		Password = password;
		Balance = balance;
		AddressId = addressId;
	}


	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
	
	

	public String getUserName() {
		return UserName;
	}


	public void setUserName(String userName) {
		UserName = userName;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	public int getBalance() {
		return Balance;
	}


	public void setBalance(int balance) {
		Balance = balance;
	}

	public int getAddressId() {
		return AddressId;
	}

	public void setAddressId(int addressId) {
		AddressId = addressId;
	}


	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", UserName=" + UserName + ", Name=" + Name + ", Password=" + Password
				+ ", Balance=" + Balance + ", AddressId=" + AddressId + "]";
	}
	
}

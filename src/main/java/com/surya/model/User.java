package com.surya.model;


public class User {
	
	 private int userId;
	 private String userName;
	 private String userPassword;
	 private String userEmail;
	 private String userDob;
	 private String userRole;
	 private String userJoiningDate;
	 private int userSalary;
	 private String userAddress;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserDob() {
		return userDob;
	}
	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserJoiningDate() {
		return userJoiningDate;
	}
	public void setUserJoiningDate(String userJoiningDate) {
		this.userJoiningDate = userJoiningDate;
	}
	public int getUserSalary() {
		return userSalary;
	}
	public void setUserSalary(int userSalary) {
		this.userSalary = userSalary;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	
	public User() {
//		super();
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userDob=" + userDob + ", userRole=" + userRole + ", userJoiningDate=" + userJoiningDate
				+ ", userSalary=" + userSalary + ", userAddress=" + userAddress + "]";
	}
	public User(int userId, String userName, String userPassword, String userEmail, String userDob, String userRole,
			String userJoiningDate, int userSalary, String userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userDob = userDob;
		this.userRole = userRole;
		this.userJoiningDate = userJoiningDate;
		this.userSalary = userSalary;
		this.userAddress = userAddress;
	}
	
	 
	
	
	
}
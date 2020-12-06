package com.techelevator.application.model;

public class Profile {
	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private int zipCode;

	
//constructor
public Profile() {
}



//getters and setters
public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public int getZipCode() {
	return zipCode;
}

public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
}




}//end of Profile 

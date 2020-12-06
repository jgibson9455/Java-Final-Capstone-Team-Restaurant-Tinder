package com.techelevator.application.model;

public class ProfilePreferences {
	private int userId;
	private int typeId;
	private int preferenceId;

//constructor
	public ProfilePreferences() {
	}

//getters and setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	}






}//end of ProfilePreferences

package com.techelevator.application.model;

public class ProfilePreferences {
	private String userName;
	private int typeId;
	private int preferenceId;
	private String typeName;

//constructor
	public ProfilePreferences() {
	}

//getters and setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "ProfilePreferences [userName=" + userName + ", typeId=" + typeId + ", preferenceId=" + preferenceId
				+ "]";
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	





}//end of ProfilePreferences

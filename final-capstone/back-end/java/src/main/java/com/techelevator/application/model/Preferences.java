package com.techelevator.application.model;

public class Preferences {
	private int preferenceId;
	private String description;
	// check that description is like or dislike
	
//constructor
	public Preferences() {
	}

	
//getters and setters
	public int getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	
	
}//last bracket of Preferences

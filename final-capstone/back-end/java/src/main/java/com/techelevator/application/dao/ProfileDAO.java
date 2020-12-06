package com.techelevator.application.dao;

import com.techelevator.application.model.Profile;

public interface ProfileDAO {

	public Profile getProfileByUserId(int userId);
	public Profile makeProfileFromUser(int userId);
	public void populateUserProfile(Profile profile);
	
	}

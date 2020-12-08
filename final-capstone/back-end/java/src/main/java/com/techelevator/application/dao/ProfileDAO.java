package com.techelevator.application.dao;

import com.techelevator.application.model.Profile;
import com.techelevator.security.model.User;

public interface ProfileDAO {

	public Profile getProfileByUserId(int userId);
	public void makeProfile(Profile profile);
	public void populateUserProfile(Profile profile);	
}

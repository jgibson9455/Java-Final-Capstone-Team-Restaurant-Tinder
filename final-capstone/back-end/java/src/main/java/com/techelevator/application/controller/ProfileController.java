package com.techelevator.application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.application.dao.ProfileDAO;
import com.techelevator.application.model.Profile;
import com.techelevator.security.model.User;

@RestController
@CrossOrigin
public class ProfileController {

	private ProfileDAO profileDAO;
	
	public ProfileController(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}
	
	@RequestMapping(path="/profile/{id}", method= RequestMethod.GET)
	public Profile getProfileById(@PathVariable int id) {
		return profileDAO.getProfileByUserId(id);
	}
	
	@RequestMapping(path="/profile", method=RequestMethod.POST)
	public Profile createProfileFromUserId(@RequestBody User user) {
		return profileDAO.makeProfileFromUser(user.getId().intValue()); // doublecheck this - int to long conversion
	}
	
	@RequestMapping(path="/profile/{id}", method=RequestMethod.PUT)
	public void updateProfile(@PathVariable int id, @RequestBody Profile profile) {
		profileDAO.populateUserProfile(profile);
	}
}

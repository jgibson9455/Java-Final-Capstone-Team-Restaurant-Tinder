package com.techelevator.application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.application.dao.ProfileDAO;
import com.techelevator.application.model.Profile;
import com.techelevator.security.dao.UserDAO;
import com.techelevator.security.model.User;

@RestController
@CrossOrigin
public class ProfileController {

	private ProfileDAO profileDAO;
	
	public ProfileController(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}
	
	@RequestMapping(path = "/profile/search", method = RequestMethod.GET)
	public Profile getProfileByUserName(@RequestParam(value = "userName", defaultValue = "") String userName) {
		return profileDAO.findByUsername(userName);
	}
	
	@RequestMapping(path="/profile/{id}", method= RequestMethod.GET)
	public Profile getProfileById(@PathVariable int id) {
		return profileDAO.getProfileByUserId(id);
	}
	
	@RequestMapping(path="/profile", method=RequestMethod.POST)
	public void createProfileFromUsername(@RequestBody Profile profile) {
		profileDAO.makeProfile(profile);
	}
	
	@RequestMapping(path="/profile", method=RequestMethod.PUT)
	public void updateProfile(@RequestBody Profile profile) {
		profileDAO.populateUserProfile(profile);
	}
}

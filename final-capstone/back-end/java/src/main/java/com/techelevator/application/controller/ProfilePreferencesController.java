package com.techelevator.application.controller;

import java.security.Timestamp;
import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.application.dao.ProfilePreferencesDAO;
import com.techelevator.application.model.ProfilePreferences;

@RestController
@CrossOrigin
public class ProfilePreferencesController {

	private ProfilePreferencesDAO profilePreferencesDAO;
	
	
	
	public ProfilePreferencesController(ProfilePreferencesDAO profilePreferencesDAO) {
		this.profilePreferencesDAO = profilePreferencesDAO;
	}
	
	@RequestMapping(path="/profile/preferences/search", method=RequestMethod.GET)
	public List<ProfilePreferences> getPreferencesByUserName(@RequestParam(value="userName", defaultValue="") String userName){
		return profilePreferencesDAO.getPreferencesByUserName(userName);
	}
	
	@RequestMapping(path="/profile/preferences", method=RequestMethod.POST)
	public void addPreference(@RequestBody ProfilePreferences aProfilePreference) {
		System.out.println("/profile/preferences - " + aProfilePreference);
		profilePreferencesDAO.addPrefererence(aProfilePreference);
	}
	
	@RequestMapping(path="/profile/preferences", method=RequestMethod.PUT) 
	public void updatePreference(@RequestBody ProfilePreferences profilePreferences) {
		profilePreferencesDAO.updatePreference(profilePreferences);
	}
	
	@RequestMapping(path="/profile/preferences", method=RequestMethod.DELETE) 
	public void deletePreference(@RequestBody ProfilePreferences profilePreferences) {
		profilePreferencesDAO.deletePreference(profilePreferences);
	}
	
	static void logRequest(String message) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // why doesnt it like this?
		System.out.println(timestamp + " - " + message);
	}
	
}//end of ProfilePreferencesController

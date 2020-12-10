package com.techelevator.application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	//TODO: REWORK THESE AND DAOS
	@RequestMapping(path="/profile/preferences/{id}", method=RequestMethod.POST)
	public ProfilePreferences addPreference(@RequestBody ProfilePreferences profilePreferences) {
		return profilePreferencesDAO.addPrefererence(profilePreferences.getUserName(), profilePreferences.getPreferenceId(), profilePreferences.getTypeId());
	}
	
	//TODO: REWORK THESE AND DAOS
	@RequestMapping(path="/profile/preferences/{id}", method=RequestMethod.PUT) 
	public void updatePreference(@PathVariable int id, @RequestBody ProfilePreferences profilePreferences) {
		profilePreferencesDAO.updatePreference(profilePreferences);
	}
	
	//TODO: REWORK THESE AND DAOS
	@RequestMapping(path="/profile/preference/{id}", method=RequestMethod.DELETE) 
	public void deletePreference(@PathVariable ProfilePreferences profilePreferences) {
		
	}
}//end of ProfilePreferencesController

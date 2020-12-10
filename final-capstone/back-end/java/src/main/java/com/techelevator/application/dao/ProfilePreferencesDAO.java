package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.ProfilePreferences;

public interface ProfilePreferencesDAO {
	public List<ProfilePreferences> getPreferencesByUserName(String userName);
	public void addPrefererence(ProfilePreferences profilePreference);
	public void updatePreference(ProfilePreferences profilePreference);
	public void deletePreference(ProfilePreferences profilePreference);
}

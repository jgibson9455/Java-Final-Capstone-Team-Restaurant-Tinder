package com.techelevator.application.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.application.dao.ProfilePreferencesDAO;
import com.techelevator.application.model.ProfilePreferences;

@Component
public class JDBCProfilePreferencesDAO implements ProfilePreferencesDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProfilePreferencesDAO(DataSource source) {
		this.jdbcTemplate = new JdbcTemplate(source);
	}
	
	@Override
	public List<ProfilePreferences> getPreferencesByUserName(String userName){
		String query = "SELECT * FROM profile_preferences WHERE user_name = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, userName);
		
		List<ProfilePreferences> preferences = new ArrayList<>();
		while(rowSet.next()) {
			ProfilePreferences preference = mapRowToProfilePreference(rowSet);
			preferences.add(preference);
		}
		
		return preferences;
	}
	
	@Override // edited for Frank's method
	public void addPrefererence(ProfilePreferences aPreference) {
		String query = "INSERT INTO profile_preferences VALUES (?, ?, ?, ?) "
					+  "ON CONFLICT (user_name, type_id, type_name) "
					+  "DO UPDATE SET preference_id = ?";
		try {
			jdbcTemplate.update(query, aPreference.getUserName(), aPreference.getTypeId(), 
					aPreference.getPreferenceId(), aPreference.getPreferenceId(), aPreference.getTypeName());
		}
		catch(DataAccessException exceptionObject) {
			System.out.println("SQL Exception when adding preference: " + aPreference + 
					"\nError message:  " + exceptionObject.getMessage());
		}//catch end
		
	}//method end

	@Override
	public void updatePreference(ProfilePreferences profilePreference) {
		String query = "UPDATE profile_preferences SET preference_id = ? WHERE user_name= ? AND type_id = ?";
		jdbcTemplate.update(query, profilePreference.getPreferenceId(), profilePreference.getUserName(), profilePreference.getTypeId());
	}
	
	@Override
	public void deletePreference(ProfilePreferences profilePreference) {
		String query = "DELETE FROM profile_preferences WHERE user_name = ? AND type_id = ?";
		jdbcTemplate.update(query, profilePreference.getUserName(), profilePreference.getTypeId()); // double check me
	}
	

	private ProfilePreferences mapRowToProfilePreference(SqlRowSet rowset) {
		ProfilePreferences profilePreference = new ProfilePreferences();
		profilePreference.setUserName(rowset.getString("user_name"));
		profilePreference.setTypeId(rowset.getInt("type_id"));
		profilePreference.setPreferenceId(rowset.getInt("preference_id"));
		profilePreference.setTypeName(rowset.getString("type_name"));
		return profilePreference; // added addtl setPrefId bc of sql exception in console
	}
	
}

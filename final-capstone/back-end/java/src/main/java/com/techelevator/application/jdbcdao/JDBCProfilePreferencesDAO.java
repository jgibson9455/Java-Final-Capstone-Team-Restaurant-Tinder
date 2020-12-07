package com.techelevator.application.jdbcdao;

import javax.sql.DataSource;
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
	public ProfilePreferences addPrefererence(int userId, int preferenceId, int typeId) {
		String query = "INSERT INTO profile_preferences VALUES(?, ?, ?)";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, userId, typeId, preferenceId);
		
		if(rowSet.next()) {
			ProfilePreferences preference = mapRowToProfilePreference(rowSet);
			return preference;
		}
		
		return null;
	}

	@Override
	public void updatePreference(ProfilePreferences profilePreference) {
		String query = "UPDATE profile_preferences SET preference_id = ?";
		jdbcTemplate.update(query, profilePreference.getPreferenceId());
	}
	
	
	@Override
	public void deletePreference(ProfilePreferences profilePreference) {
		String query = "DELETE profile_preferences WHERE preference_id = ?";
		jdbcTemplate.update(query, profilePreference.getPreferenceId()); // double check me
	}
	
	

	private ProfilePreferences mapRowToProfilePreference(SqlRowSet rowset) {
		ProfilePreferences profilePreference = new ProfilePreferences();
		profilePreference.setUserId(rowset.getInt("user_id"));
		profilePreference.setTypeId(rowset.getInt("type_id"));
		profilePreference.setPreferenceId(rowset.getInt("preference_id"));

		return profilePreference;
	}
}

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

	private ProfilePreferences mapRowToProfilePreference(SqlRowSet rowset) {
		ProfilePreferences profilePreference = new ProfilePreferences();
		profilePreference.setUserId(rowset.getInt("user_id"));
		profilePreference.setTypeId(rowset.getInt("type_id"));
		profilePreference.setPreferenceId(rowset.getInt("preference_id"));

		return profilePreference;
	}
}

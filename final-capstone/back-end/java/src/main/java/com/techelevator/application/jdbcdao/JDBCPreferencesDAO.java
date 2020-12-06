package com.techelevator.application.jdbcdao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.application.dao.PreferencesDAO;
import com.techelevator.application.model.Preferences;

@Component
public class JDBCPreferencesDAO implements PreferencesDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCPreferencesDAO(DataSource source) {
		this.jdbcTemplate = new JdbcTemplate(source);
	}
	
	
	
	private Preferences mapRowToPreference(SqlRowSet rowset) {
		Preferences preference = new Preferences();
		preference.setPreferenceId(rowset.getInt("preference_id"));
		preference.setDescription(rowset.getString("description"));
		
		return preference;
	}

}

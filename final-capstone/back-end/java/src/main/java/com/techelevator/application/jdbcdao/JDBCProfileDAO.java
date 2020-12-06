package com.techelevator.application.jdbcdao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.application.dao.ProfileDAO;
import com.techelevator.application.model.Profile;

@Component
public class JDBCProfileDAO implements ProfileDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProfileDAO(DataSource source) {
		this.jdbcTemplate = new JdbcTemplate(source);
	}

	private Profile mapRowToPreference(SqlRowSet rowset) {
		Profile profile = new Profile();
		profile.setUserId(rowset.getInt("user_id"));
		profile.setFirstName(rowset.getString("first_name"));
		profile.setLastName(rowset.getString("last_name"));
		profile.setUserName(rowset.getString("user_name"));
		profile.setEmail(rowset.getString("email"));
		profile.setZipCode(rowset.getInt("zip_code"));

		return profile;
	}
}

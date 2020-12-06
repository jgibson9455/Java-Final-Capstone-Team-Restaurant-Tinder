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

	@Override
	public Profile getProfileByUserId(int userId) {
		String query = "SELECT * FROM profile WHERE user_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, userId);
		if (rowSet.next()) {
			Profile profile = mapRowToProfile(rowSet);
			return profile;
		}
		return null;
	}

	@Override
	public Profile makeProfileFromUser(int userId) {
		String query = "INSERT INTO profile (user_id, user_name) " + "SELECT u.user_id, u.username FROM users u "
				+ "WHERE u.user_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, userId);
		if (rowSet.next()) {
			Profile profile = mapRowToProfileFromUser(rowSet);
			return profile;
		}
		return null;
	}

	@Override
	public void populateUserProfile(Profile profile) {
		String query = "UPDATE profile SET first_name = ?, last_name = ?, " + "email_address = ?, zip_code = ? "
				+ "WHERE user_id = ?";
		jdbcTemplate.update(query, profile.getFirstName(), profile.getLastName(), profile.getEmail(),
				profile.getZipCode(), profile.getUserId());
		
	}
	
	

	private Profile mapRowToProfileFromUser(SqlRowSet rowset) {
		Profile profile = new Profile();
		profile.setUserId(rowset.getInt("user_id"));
		profile.setFirstName(null);
		profile.setLastName(null);
		profile.setUserName(rowset.getString("user_name"));
		profile.setEmail(null);
		profile.setZipCode(0);

		return profile;

	}

	private Profile mapRowToProfile(SqlRowSet rowset) {
		Profile profile = new Profile();
		profile.setUserId(rowset.getInt("user_id"));
		profile.setFirstName(rowset.getString("first_name"));
		profile.setLastName(rowset.getString("last_name"));
		profile.setUserName(rowset.getString("user_name"));
		profile.setEmail(rowset.getString("email_address"));
		profile.setZipCode(rowset.getInt("zip_code"));

		return profile;
	}
}

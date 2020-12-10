package com.techelevator.application.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.application.dao.MatchingResultsDAO;
import com.techelevator.application.model.MatchingResults;

@Component
public class JDBCMachingResultsDAO implements MatchingResultsDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCMachingResultsDAO(DataSource source) {
		this.jdbcTemplate = new JdbcTemplate(source);
	}

	@Override
	public void addMatchingResult(MatchingResults matchingResult) {
		String query = "INSERT INTO matching_results VALUES(?,?,?)";
		jdbcTemplate.update(query, matchingResult.getUserName(), matchingResult.getRestaurantId(),
				matchingResult.getPreferenceId());
	}

	@Override
	public List<MatchingResults> getResultsByUserName(String userName) {
		String query = "SELECT * FROM matching_results WHERE user_name = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, userName);
		List<MatchingResults> matchingResults = new ArrayList<>();

		while (rowSet.next()) {
			MatchingResults resultToAdd = mapRowToMatchingResults(rowSet);
			matchingResults.add(resultToAdd);
		}

		return matchingResults;
	}

	private MatchingResults mapRowToMatchingResults(SqlRowSet rowset) {
		MatchingResults matchingResults = new MatchingResults();
		matchingResults.setUserName(rowset.getString("user_name"));
		matchingResults.setRestaurantId(rowset.getInt("restaurant_id"));
		matchingResults.setPreferenceId(rowset.getInt("preference_id"));

		return matchingResults;
	}
}

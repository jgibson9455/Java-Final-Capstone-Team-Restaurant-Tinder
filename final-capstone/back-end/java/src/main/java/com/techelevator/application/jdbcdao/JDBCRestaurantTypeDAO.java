package com.techelevator.application.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.application.dao.RestaurantTypeDAO;
import com.techelevator.application.model.RestaurantType;

@Component
public class JDBCRestaurantTypeDAO implements RestaurantTypeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCRestaurantTypeDAO(DataSource source) {
		this.jdbcTemplate = new JdbcTemplate(source);
	}
	
	@Override
	public List<RestaurantType> getTop20Types(){
		List<RestaurantType> types = new ArrayList<>();
		
		String query = "SELECT * FROM restaurant_type WHERE isTop20 = 'y'";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
		while(rowSet.next()) {
			RestaurantType type = mapRowToRestaurantType(rowSet);
			types.add(type);
		}
		
		return types;
	}
	
	@Override
	public List<RestaurantType> getNonTop20Types(){
		List<RestaurantType> types = new ArrayList<>();
		String query = "SELECT * FROM restaurant_type WHERE isTop20 = 'n'";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
		while(rowSet.next()) {
			RestaurantType type = mapRowToRestaurantType(rowSet);
			types.add(type);
		}
		
		return types;
	}
	
	
	
	@Override
	public RestaurantType getTypeById(int id) {
		String query = "SELECT * FROM restaurant_type WHERE type_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, id);
		
		if(rowSet.next()) {
			RestaurantType type = mapRowToRestaurantType(rowSet);
			return type;
		}
		
		return null;
	}

	private RestaurantType mapRowToRestaurantType(SqlRowSet rowset) {
		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setTypeId(rowset.getInt("type_id"));
		restaurantType.setTypeName(rowset.getString("type_name"));
		restaurantType.setIsTop20(rowset.getString("isTop20"));
		return restaurantType;
	}

}

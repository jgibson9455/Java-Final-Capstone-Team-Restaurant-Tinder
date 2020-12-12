package com.techelevator.application.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.application.dao.RestaurantDAO;
import com.techelevator.application.model.Restaurant;

@Component
public class JDBCRestaurantDAO implements RestaurantDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCRestaurantDAO(DataSource source) {
		this.jdbcTemplate = new JdbcTemplate(source);
	}
	
	@Override
	public List<Restaurant> getAllRestaurants() {
		String query="SELECT * FROM restaurant";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
		List<Restaurant> restaurants = new ArrayList<>();
			while(rowSet.next()) {
				Restaurant restaurantToAdd = mapRowToRestaurant(rowSet);
				restaurants.add(restaurantToAdd);
			}
		return restaurants;
	}

	@Override
	public Restaurant getRestaurantById(int id) {
		String query ="SELECT * FROM restaurant WHERE restaurant_id=?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, id);
			if(rowSet.next()) {
				Restaurant restaurant = mapRowToRestaurant(rowSet);
				return restaurant;
			}
		
		return null;
	}
	
	@Override
	public List<Restaurant> getRestaurantsByType(int typeId){
		List<Restaurant> restaurantType = new ArrayList<>();
		String query = "SELECT * FROM restaurant WHERE type_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, typeId);
		
		while(rowSet.next()) {
			Restaurant restaurantToAdd = mapRowToRestaurant(rowSet);
			restaurantType.add(restaurantToAdd);
		}
		
		return restaurantType;
	}
	
	private Restaurant mapRowToRestaurant(SqlRowSet rowset) {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(rowset.getInt("restaurant_id"));
		restaurant.setRestaurantName(rowset.getString("restaurant_name"));
		restaurant.setRestaurantDescrip(rowset.getString("description"));
		restaurant.setZipCode(rowset.getInt("zip_code"));
		restaurant.setCity(rowset.getString("city"));
		restaurant.setPhoneNumber(rowset.getString("phone_number"));
		restaurant.setImageLink(rowset.getString("image_link"));
		restaurant.setAddress(rowset.getString("address"));
		restaurant.setTypeId(rowset.getInt("type_id"));

		return restaurant;
	}

	

}

package com.techelevator.application.jdbcdao;

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

	private Restaurant mapRowToRestaurant(SqlRowSet rowset) {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(rowset.getInt("restaurant_id"));
		restaurant.setRestaurantName(rowset.getString("restaurant_name"));
		restaurant.setRestaurantDescrip(rowset.getString("description"));
		restaurant.setZipCode(rowset.getInt("zip_code"));
		restaurant.setPhoneNumber(rowset.getString("phone_number"));
		restaurant.setImageLink(rowset.getString("image_link"));
		restaurant.setAddress(rowset.getString("address"));
		restaurant.setTypeId(rowset.getInt("type_id"));

		return restaurant;
	}

}

package com.techelevator.application.jdbcdao;

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

	private RestaurantType mapRowToRestaurant(SqlRowSet rowset) {
		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setTypeId(rowset.getInt("type_id"));
		restaurantType.setTypeName(rowset.getString("type_name"));

		return restaurantType;
	}

}

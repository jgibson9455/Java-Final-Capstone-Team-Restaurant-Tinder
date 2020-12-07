package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.RestaurantType;

public interface RestaurantTypeDAO {
	
	List<RestaurantType> getAllTypes();
	RestaurantType getTypeById(int id);
}

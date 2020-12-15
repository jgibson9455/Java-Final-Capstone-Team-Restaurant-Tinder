package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.RestaurantType;

public interface RestaurantTypeDAO {
	
	List<RestaurantType> getTop20Types();
	List<RestaurantType> getNonTop20Types();
	RestaurantType getTypeById(int id);
}

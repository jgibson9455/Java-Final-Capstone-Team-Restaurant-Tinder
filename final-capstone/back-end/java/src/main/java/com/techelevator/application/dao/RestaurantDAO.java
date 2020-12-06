package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.Restaurant;

public interface RestaurantDAO {

	public List<Restaurant> getAllRestaurants();
	public Restaurant getRestaurantById(int id);
	
	
	
}

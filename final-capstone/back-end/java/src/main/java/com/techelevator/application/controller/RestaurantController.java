package com.techelevator.application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.application.dao.RestaurantDAO;
import com.techelevator.application.model.Restaurant;

@RestController
@CrossOrigin
public class RestaurantController {

	private RestaurantDAO restaurantDAO;
	
	public RestaurantController(RestaurantDAO restaurantDAO) {
		 this.restaurantDAO = restaurantDAO;
	}
	
	@RequestMapping(path="/restaurants", method=RequestMethod.GET)
	public List<Restaurant> getAllRestaurants() {
		return restaurantDAO.getAllRestaurants();
	}
	
	@RequestMapping(path="/restaurant/{id}", method=RequestMethod.GET)
	public Restaurant getRestaurantById(@PathVariable int id) {
		return restaurantDAO.getRestaurantById(id);
	}
}

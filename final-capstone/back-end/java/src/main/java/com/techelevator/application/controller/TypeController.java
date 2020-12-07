package com.techelevator.application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.application.dao.RestaurantTypeDAO;
import com.techelevator.application.model.RestaurantType;

@RestController
@CrossOrigin
public class TypeController {
	
	private RestaurantTypeDAO typeDao;
	
	public TypeController(RestaurantTypeDAO typeDao) {
		this.typeDao = typeDao;
	}
	
	@RequestMapping(path="/types", method= RequestMethod.GET)
	public List<RestaurantType> getAllTypes(){
		return typeDao.getAllTypes();
	}
	
	@RequestMapping(path="/type/{id}", method= RequestMethod.GET)
	public RestaurantType getTypeById(@PathVariable int id) {
		return typeDao.getTypeById(id);
	}
}

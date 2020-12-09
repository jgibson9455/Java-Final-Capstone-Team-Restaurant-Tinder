package com.techelevator.application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.security.dao.UserDAO;
import com.techelevator.security.model.User;

@RestController
@CrossOrigin
public class UserController {

	private UserDAO userDao;

	public UserController(UserDAO userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(path = "/user/search", method = RequestMethod.GET)
	public User getUserByUserName(@RequestParam(value = "userName", defaultValue = "") String userName) {
		return userDao.findByUsername(userName);
	}

	/*
	 * @RequestMapping(path = "/user/search", method = RequestMethod.GET) public int
	 * getUserIdByUserName(@RequestParam(value = "userName", defaultValue = "")
	 * String userName) { return userDao.findIdByUsername(userName); }
	 */

	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable long id) {
		return userDao.getUserById(id);
	}
}

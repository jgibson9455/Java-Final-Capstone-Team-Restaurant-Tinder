package com.techelevator.application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.application.dao.MatchingResultsDAO;
import com.techelevator.application.model.MatchingResults;

@RestController
@CrossOrigin
public class MatchingResultsController {
	
	private MatchingResultsDAO matchingDao;
	
	public MatchingResultsController(MatchingResultsDAO matchingDao) {
		this.matchingDao = matchingDao;
	}
	
	@RequestMapping(path = "/matchingresults/search", method = RequestMethod.GET)
	public List<MatchingResults> getMatchingResultsByUserName(@RequestParam(value = "userName", defaultValue = "") String userName){
		return matchingDao.getResultsByUserName(userName);
	}
	
	@RequestMapping(path = "/matchingresults", method = RequestMethod.POST)
	public void addMatchingResult(@RequestBody MatchingResults result) {
		matchingDao.addMatchingResult(result);
	}
}

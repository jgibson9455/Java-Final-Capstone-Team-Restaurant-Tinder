package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.MatchingResults;

public interface MatchingResultsDAO {
	public void addMatchingResult(MatchingResults matchingResult);
	public List<MatchingResults> getResultsByUserName(String userName);
}

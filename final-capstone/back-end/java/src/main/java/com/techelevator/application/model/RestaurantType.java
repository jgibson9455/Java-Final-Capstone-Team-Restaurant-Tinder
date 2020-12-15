package com.techelevator.application.model;

public class RestaurantType {
	private int typeId;
	private String typeName;
	private String isTop20;
	
	
//constructor
	public RestaurantType() {
	}

	
//getters and setters
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getIsTop20() {
		return isTop20;
	}


	public void setIsTop20(String isTop20) {
		this.isTop20 = isTop20;
	}
	
	
	
	
	
	
	
	
}// end of RestaurantType

package com.techelevator.application.model;

public class Restaurant {
	private int id;
	private String name;
	private String descrip;
	private int zipCode;
	private String phoneNumber;
	private String imageLink;
	private String address;
	private int typeId;
	
//constructor
	public Restaurant() {
	}

	
//getters and setters
	public int getRestaurantId() {
		return id;
	}

	public void setRestaurantId(int restaurantId) {
		this.id = restaurantId;
	}

	public String getRestaurantName() {
		return name;
	}

	public void setRestaurantName(String restaurantName) {
		this.name = restaurantName;
	}

	public String getRestaurantDescrip() {
		return descrip;
	}

	public void setRestaurantDescrip(String restaurantDescrip) {
		this.descrip = restaurantDescrip;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	

	
	
	
	
	
	
} // final Restaurant bracket

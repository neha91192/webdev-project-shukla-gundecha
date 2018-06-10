package com.neu.webdev2018summer1.thefoodexplorer.models;

import javax.persistence.Entity;

@Entity
public class Customer extends User {
	private String customerId;
	private String locationArea;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLocationArea() {
		return locationArea;
	}

	public void setLocationArea(String locationArea) {
		this.locationArea = locationArea;
	}

}

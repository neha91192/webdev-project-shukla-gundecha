package com.neu.webdev2018summer1.thefoodexplorer.models;

import javax.persistence.Entity;

@Entity
public class Customer extends User {
	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



}

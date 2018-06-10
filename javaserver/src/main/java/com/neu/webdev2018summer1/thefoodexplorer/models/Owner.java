package com.neu.webdev2018summer1.thefoodexplorer.models;

import javax.persistence.Entity;

@Entity
public class Owner extends User {
	private String ownerId;
	private int term;
	private int restaurantId;

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

}

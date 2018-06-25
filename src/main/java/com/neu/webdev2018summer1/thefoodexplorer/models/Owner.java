package com.neu.webdev2018summer1.thefoodexplorer.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Owner extends User {
	private String ownerId;
	private int term;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.PERSIST)
	private Restaurant restaurant;

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

	/**
	 * Sets restaurant for this owner
	 * 
	 * @return
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Gets restaurant for this owner
	 * 
	 * @param restaurant
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}

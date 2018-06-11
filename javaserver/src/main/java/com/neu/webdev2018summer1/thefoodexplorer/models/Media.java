package com.neu.webdev2018summer1.thefoodexplorer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Media {
	/**
	 * Unique identifier for media
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mediaId;
	/**
	 * Restaurant for which this photo has been uploaded
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId", insertable = true, updatable = true)
	private Restaurant restaurant;
	/**
	 * Image of the restaurant
	 */
	private String image;

	/**
	 * Returns restaurant instance
	 * 
	 * @return
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Sets restaurant instance
	 * 
	 * @param restaurant
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Returns image of the restaurant
	 * 
	 * @return
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets image of the restaurant
	 * 
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Returns id of this media
	 * 
	 * @return
	 */
	public int getMediaId() {
		return mediaId;
	}

	/**
	 * Sets id of this media
	 * 
	 * @param mediaId
	 */
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
}

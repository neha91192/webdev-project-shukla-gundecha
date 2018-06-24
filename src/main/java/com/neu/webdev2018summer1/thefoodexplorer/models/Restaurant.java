package com.neu.webdev2018summer1.thefoodexplorer.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Restaurant {

	/**
	 * id of restaurant
	 */
	@Id
	private int restaurantId;

	/**
	 * rating of restaurant
	 */
	private int rating;

	/**
	 * Owner of this restaurant
	 */
	@OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private Owner owner;
	/**
	 * Name of this restaurant
	 */
	private String name;
	/**
	 * Locality of this restaurant used as search criteria
	 */
	private String locationArea;
	/**
	 * Contact of restaurant
	 */
	private String phoneNumber;
	/**
	 * One restaurant has many reservations
	 */
	@OneToMany(mappedBy = "restaurant", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Reservation> reservations;
	/**
	 * One restaurant has many reviews
	 */
	@OneToMany(mappedBy = "restaurant", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Review> reviews;
	/**
	 * One restaurant has many media
	 */
	@OneToMany(mappedBy = "restaurant", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Media> media;
	/**
	 * Many restaurants fall in several categories
	 */
	@ManyToMany(mappedBy = "restaurants")
	private List<Category> categories;

	/**
	 * Returns Id of restaurant
	 * 
	 * @return
	 */
	public int getRestaurantId() {
		return restaurantId;
	}

	/**
	 * Sets Id of restaurant
	 * 
	 * @param restaurantId
	 */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * Returns rating of restaurant
	 * 
	 * @return
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets rating of restaurant
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Returns owner of this restaurant
	 * 
	 * @return
	 */
	public Owner getOwner() {
		return owner;
	}

	/**
	 * Sets owner of this restaurant
	 * 
	 * @param owner
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	/**
	 * Returns name of this restaurant
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of this restaurant
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns area of this restaurant used in search feature
	 * 
	 * @return
	 */
	public String getLocationArea() {
		return locationArea;
	}

	/**
	 * Sets area of this restaurant
	 * 
	 * @param locationArea
	 */
	public void setLocationArea(String locationArea) {
		this.locationArea = locationArea;
	}

	/**
	 * Gets phone number of this restaurant
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number of this restaurant
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Returns reservations of this restaurant
	 * 
	 * @param reservations
	 */
	public List<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * Sets reservations of this restaurant
	 * 
	 * @param reservations
	 */
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	/**
	 * Returns reviews of this restaurant
	 * 
	 * @return
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * Sets reviews of this restaurant
	 * 
	 * @param reviews
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Returns media of this restaurant
	 * 
	 * @return
	 */
	public List<Media> getMedia() {
		return media;
	}

	/**
	 * Sets media of this restaurant
	 * 
	 * @param media
	 */
	public void setMedia(List<Media> media) {
		this.media = media;
	}

	/**
	 * Returns category of this restaurant
	 * 
	 * @return
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * Sets categories of this restaurant
	 * 
	 * @param categories
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}

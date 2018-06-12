package com.neu.webdev2018summer1.thefoodexplorer.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {
	/**
	 * Unique identifier for restaurant reservation
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservationId;
	/**
	 * Maximum number of people valid for this reservation
	 */
	private int noOfPeople;
	/**
	 * Reservation timings
	 */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate time;
	/**
	 * User related to this reservation
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id", referencedColumnName = "userId", insertable = true, updatable = true)
	private User user;
	/**
	 * Restaurant related to this reservation
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId", insertable = true, updatable = true)
	private Restaurant restaurant;

	/**
	 * Returns reservation Id
	 * 
	 * @return
	 */
	public int getReservationId() {
		return reservationId;
	}

	/**
	 * Sets reservation id
	 * 
	 * @param reservationId
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * Returns number of people for this reservation
	 * 
	 * @return
	 */
	public int getNoOfPeople() {
		return noOfPeople;
	}

	/**
	 * Sets number of people for this reservation
	 * 
	 * @param noOfPeople
	 */
	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}

	/**
	 * Returns timing details of this reservation
	 * 
	 * @return
	 */
	public LocalDate getTime() {
		return time;
	}

	/**
	 * Sets timing details of this reservation
	 * 
	 * @param time
	 */
	public void setTime(LocalDate time) {
		this.time = time;
	}

	/**
	 * Returns user who has requested for this reservation
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets user who has requested for this reservation
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns restaurant of this reservation
	 * 
	 * @return
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Sets restaurant for this reservation
	 * 
	 * @param restaurant
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}

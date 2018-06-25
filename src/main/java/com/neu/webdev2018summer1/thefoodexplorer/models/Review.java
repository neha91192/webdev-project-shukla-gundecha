package com.neu.webdev2018summer1.thefoodexplorer.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	/**
	 * Unique identifier of review
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	/**
	 * Restaurant for which review has be written
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId", insertable = true, updatable = true)
	private Restaurant restaurant;
	/**
	 * Rating given by the user
	 */
	private int rating;
	/**
	 * Author of this review
	 */
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id", referencedColumnName = "userId", insertable = true, updatable = true)
	private Customer user;
	/**
	 * Review content
	 */
	private String content;
	/**
	 * Likes given by other readers on this review
	 */
	private int likes;
	/**
	 * Records time when this review was given
	 */
	@CreationTimestamp
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate age;

	/**
	 * Returns identifier of review
	 * 
	 * @return
	 */
	public int getReviewId() {
		return reviewId;
	}

	/**
	 * Sets id of review
	 * 
	 * @param reviewId
	 */
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	/**
	 * Returns restaurant for which this review has been written.
	 * 
	 * @return
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Sets restaurant for which this review has been written
	 * 
	 * @param restaurant
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Gets rating given by the user
	 * 
	 * @return
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets rating given by the user
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Returns user who has given the review
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets user who has given the review
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns content of the review
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets content of the review
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Returns likes given by readers of this review
	 * 
	 * @return
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * Sets likes given by readers of this review
	 * 
	 * @param likes
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * Gets age of this review
	 * 
	 * @return
	 */
	public LocalDate getAge() {
		return age;
	}

	/**
	 * Sets age of this review
	 * 
	 * @param age
	 */
	public void setAge(LocalDate age) {
		this.age = age;
	}

}

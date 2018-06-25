package com.neu.webdev2018summer1.thefoodexplorer.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer extends User {
	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JsonIgnore()
	@JoinTable(name = "follower", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "followerId") })
	List<Customer> followers;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JsonIgnore()
	@JoinTable(name = "follower", joinColumns = @JoinColumn(name = "followerId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	List<Customer> following;

	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Review> reviews;

	public List<Customer> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Customer> followers) {
		this.followers = followers;
	}

	public List<Customer> getFollowing() {
		return following;
	}

	public void setFollowing(List<Customer> following) {
		this.following = following;
	}

	/**
	 * Gets list of reviews written by this user
	 * 
	 * @return
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * Sets list of reviews written by this user
	 * 
	 * @param reviews
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}

package com.neu.webdev2018summer1.thefoodexplorer.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	@JoinTable(name = "follower", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "followerId") })
	List<Customer> followers;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "follower", joinColumns = @JoinColumn(name = "followerId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	List<Customer> following;

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

}

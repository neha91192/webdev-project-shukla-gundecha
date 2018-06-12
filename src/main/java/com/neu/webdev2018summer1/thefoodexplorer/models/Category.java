package com.neu.webdev2018summer1.thefoodexplorer.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	/**
	 * Unique identifier for this category
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	/**
	 * Name of this category
	 */
	private String name;
	/**
	 * List of restaurants belonging to this category
	 */
	@ManyToMany
	@JsonIgnore
	@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId", insertable = true, updatable = true)
	private List<Restaurant> restaurants;

	/**
	 * Returns id of category
	 * 
	 * @return
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets category id
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Returns name of the category
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of the category
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns restaurants belonging to this category
	 * 
	 * @return
	 */
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	/**
	 * Sets restaurants belonging to this category
	 * 
	 * @param restaurants
	 */
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}

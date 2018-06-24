package com.neu.webdev2018summer1.thefoodexplorer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.neu.webdev2018summer1.thefoodexplorer.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
	@Query("SELECT r FROM Review r WHERE r.userId=:userId")
	Iterable<Review> searchReviewByCustomer(@Param("userId") int userId);

	@Query("SELECT r FROM Review r WHERE r.restaurantId=:restaurantId")
	Iterable<Review> searchReviewByRestaurant(@Param("restaurantId") int restaurantId);
}

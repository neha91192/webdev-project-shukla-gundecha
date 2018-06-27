package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.webdev2018summer1.thefoodexplorer.models.Restaurant;
import com.neu.webdev2018summer1.thefoodexplorer.models.Review;
import com.neu.webdev2018summer1.thefoodexplorer.models.User;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.RestaurantRepository;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.ReviewRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	RestaurantRepository restaurantRepository;

	/**
	 * Creates review object in database
	 * 
	 * @param review
	 * @return
	 */
	@PostMapping("/api/restaurant/{restaurantId}/review")
	public Review createReview(@RequestBody Review review, @PathVariable("restaurantId") int restaurantId,
			HttpSession session, HttpServletResponse response) {

		User user = (User) session.getAttribute("currentUser");
		if (user != null) {
			com.neu.webdev2018summer1.thefoodexplorer.models.Customer newUser = new com.neu.webdev2018summer1.thefoodexplorer.models.Customer();
			newUser.setUserId(user.getUserId());
			review.setCustomer(newUser);

			Optional<Restaurant> restaurantData = restaurantRepository.findById(restaurantId);
			if (restaurantData.isPresent()) {
				review.setRestaurant(restaurantData.get());
			} else {
				Restaurant restaurantObj = new Restaurant();
				restaurantObj.setRestaurantId(restaurantId);
				restaurantObj.setName(review.getRestaurant().getName());
				restaurantObj.setLocationArea(review.getRestaurant().getLocationArea());
				restaurantRepository.save(restaurantObj);
				review.setRestaurant(restaurantObj);
			}
			Review newReview = reviewRepository.save(review);
			return newReview;

		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

	}

	/**
	 * Returns all Review in database
	 * 
	 * @return
	 */
	@GetMapping("/api/review")
	public Iterable<Review> findAllReviews() {
		return reviewRepository.findAll();
	}

	/**
	 * Returns all Review in database
	 * 
	 * @return
	 */
	@GetMapping("/api/customer/{userId}/review")
	public Iterable<Review> findAllReviewsForCustomer(@PathVariable("userId") int userId) {

		return reviewRepository.searchReviewByCustomer(userId);
	}

	/**
	 * Returns all Review in database
	 * 
	 * @return
	 */
	@GetMapping("/api/restaurant/{restaurantId}/review")
	public Iterable<Review> findAllReviewsForRestaurant(@PathVariable("restaurantId") int restaurantId) {
		Iterable<Review> response = reviewRepository.searchReviewByRestaurant(restaurantId);
		return response;
	}

	/**
	 * Returns Review details by Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/review/{reviewId}")
	public Review readReview(@PathVariable("reviewId") int id) {
		Optional<Review> review = reviewRepository.findById(id);
		if (review.isPresent()) {
			return review.get();
		} else {
			return null;
		}
	}

	/**
	 * Updates review object in the database
	 * 
	 * @param id
	 * @param review
	 * @return
	 */
	@PutMapping("api/review/{reviewId}")
	public Review updateReview(@PathVariable("reviewId") int id, @RequestBody Review review) {

		Optional<Review> data = reviewRepository.findById(id);
		if (data.isPresent()) {
			Review existingReview = data.get();
			existingReview.setAge(review.getAge());
			existingReview.setContent(review.getContent());
			existingReview.setLikes(review.getLikes());
			// set average rating here
			existingReview.setRating(review.getRating());

			reviewRepository.save(existingReview);
			return existingReview;
		} else
			return null;
	}

	/**
	 * Deletes review by id
	 * 
	 * @param id
	 */
	@DeleteMapping("/api/review/{reviewId}")
	public void deleteReview(@PathVariable("reviewId") int id) {
		reviewRepository.deleteById(id);
	}
}

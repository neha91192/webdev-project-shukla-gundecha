package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.webdev2018summer1.thefoodexplorer.models.Review;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.ReviewRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	/**
	 * Creates review object in database
	 * 
	 * @param review
	 * @return
	 */
	@PostMapping("/api/review")
	public Review createReview(@RequestBody Review review) {
		return reviewRepository.save(review);
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

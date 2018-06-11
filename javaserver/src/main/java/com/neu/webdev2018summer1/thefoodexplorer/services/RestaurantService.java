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

import com.neu.webdev2018summer1.thefoodexplorer.models.Restaurant;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.RestaurantRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RestaurantService {
	@Autowired
	RestaurantRepository restaurantRepository;

	/**
	 * Creates restaurant object in database
	 * 
	 * @param restaurant
	 * @return
	 */
	@PostMapping("/api/restaurant")
	public Restaurant createRestaurant(@RequestBody Restaurant reservation) {
		return restaurantRepository.save(reservation);
	}

	/**
	 * Returns all restaurant in database
	 * 
	 * @return
	 */
	@GetMapping("/api/restaurant")
	public Iterable<Restaurant> findAllRestaurants() {
		return restaurantRepository.findAll();
	}

	/**
	 * Returns Restaurant details by Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/restaurant/{restaurantId}")
	public Restaurant readRestaurant(@PathVariable("reservationId") int id) {
		Optional<Restaurant> reservation = restaurantRepository.findById(id);
		if (reservation.isPresent()) {
			return reservation.get();
		} else {
			return null;
		}
	}

	/**
	 * Updates Restaurant object in the database
	 * 
	 * @param id
	 * @param restaurant
	 * @return
	 */
	@PutMapping("api/restaurant/{restaurantId}")
	public Restaurant updateRestaurant(@PathVariable("restaurantId") int id, @RequestBody Restaurant restaurant) {

		Optional<Restaurant> data = restaurantRepository.findById(id);
		if (data.isPresent()) {
			Restaurant existingRestaurant = data.get();
			existingRestaurant.setName(restaurant.getName());
			existingRestaurant.setOwner(restaurant.getOwner());
			existingRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
			existingRestaurant.setRating(restaurant.getRating());
			existingRestaurant.setLocationArea(restaurant.getLocationArea());
			existingRestaurant.setReviews(restaurant.getReviews());
			existingRestaurant.setReservations(restaurant.getReservations());
			existingRestaurant.setMedia(restaurant.getMedia());
			existingRestaurant.setCategories(restaurant.getCategories());

			restaurantRepository.save(existingRestaurant);
			return existingRestaurant;
		} else
			return null;
	}

	/**
	 * Deletes Restaurant by id
	 * 
	 * @param id
	 */
	@DeleteMapping("/api/restaurant/{restaurantId}")
	public void deleteReservation(@PathVariable("restaurantId") int id) {
		restaurantRepository.deleteById(id);
	}
}

package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.List;
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

import com.neu.webdev2018summer1.thefoodexplorer.models.Media;
import com.neu.webdev2018summer1.thefoodexplorer.models.Restaurant;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.MediaRepository;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.RestaurantRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MediaService {
	@Autowired
	MediaRepository mediaRepository;
	@Autowired
	RestaurantRepository restaurantRepository;

	/**
	 * Creates Media object in database
	 * 
	 * @param Media
	 * @return
	 */
	@PostMapping("/api/restaurant/{restaurantId}/media")
	public Media createMedia(@RequestBody Media media, @PathVariable("restaurantId") int id) {
		Optional<Restaurant> restaurantData = restaurantRepository.findById(id);
		if (restaurantData.isPresent()) {
			Restaurant restaurant = restaurantData.get();
			media.setRestaurant(restaurant);
		} else {
			Restaurant restaurantObj = new Restaurant();
			restaurantObj.setRestaurantId(id);
			restaurantRepository.save(restaurantObj);
			media.setRestaurant(restaurantObj);
		}
		return mediaRepository.save(media);
	}

	/**
	 * Returns all media in database
	 * 
	 * @return
	 */
	@GetMapping("/api/media")
	public Iterable<Media> findAllMedia() {
		return mediaRepository.findAll();
	}

	/**
	 * Returns media details by Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/media/{mediaId}")
	public Media readMedia(@PathVariable("mediaId") int id) {
		Optional<Media> media = mediaRepository.findById(id);
		if (media.isPresent()) {
			return media.get();
		} else {
			return null;
		}
	}

	/**
	 * Returns media details by Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/restaurant/{restaurantId}/media")
	public List<Media> findAllMediaForRestaurant(@PathVariable("restaurantId") int id) {
		String mediaType = "Menu";
		return (List<Media>) mediaRepository.findMediaForRestaurant(id, mediaType);

	}

	/**
	 * Updates media object in the database
	 * 
	 * @param id
	 * @param media
	 * @return
	 */
	@PutMapping("api/media/{mediaId}")
	public Media updateMedia(@PathVariable("mediaId") int id, @RequestBody Media media) {

		Optional<Media> data = mediaRepository.findById(id);
		if (data.isPresent()) {
			Media existingMedia = data.get();
			existingMedia.setImage(media.getImage());
			existingMedia.setRestaurant(media.getRestaurant());

			mediaRepository.save(existingMedia);
			return existingMedia;
		} else
			return null;
	}

	/**
	 * Deletes media by id
	 * 
	 * @param id
	 */
	@DeleteMapping("/api/media/{mediaId}")
	public void deleteMedia(@PathVariable("mediaId") int id) {
		mediaRepository.deleteById(id);
	}
}

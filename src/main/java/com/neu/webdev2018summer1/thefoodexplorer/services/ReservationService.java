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

import com.neu.webdev2018summer1.thefoodexplorer.models.Reservation;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.ReservationRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservationService {
	@Autowired
	ReservationRepository reservationRepository;

	/**
	 * Creates reservation object in database
	 * 
	 * @param reservation
	 * @return
	 */
	@PostMapping("/api/reservation")
	public Reservation createReservation(@RequestBody Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	/**
	 * Returns all reservation in database
	 * 
	 * @return
	 */
	@GetMapping("/api/reservation")
	public Iterable<Reservation> findAllReservations() {
		return reservationRepository.findAll();
	}

	/**
	 * Returns reservation details by Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/reservation/{reservationId}")
	public Reservation readReservation(@PathVariable("reservationId") int id) {
		Optional<Reservation> reservation = reservationRepository.findById(id);
		if (reservation.isPresent()) {
			return reservation.get();
		} else {
			return null;
		}
	}

	/**
	 * Updates reservation object in the database
	 * 
	 * @param id
	 * @param reservation
	 * @return
	 */
	@PutMapping("api/reservation/{reservationId}")
	public Reservation updateReservation(@PathVariable("reservationId") int id, @RequestBody Reservation reservation) {

		Optional<Reservation> data = reservationRepository.findById(id);
		if (data.isPresent()) {
			Reservation existingReservation = data.get();
			existingReservation.setNoOfPeople(reservation.getNoOfPeople());
			existingReservation.setTime(reservation.getTime());
			reservationRepository.save(existingReservation);
			return existingReservation;
		} else
			return null;
	}

	/**
	 * Deletes reservation by id
	 * 
	 * @param id
	 */
	@DeleteMapping("/api/reservation/{reservationId}")
	public void deleteReservation(@PathVariable("reservationId") int id) {
		reservationRepository.deleteById(id);
	}
}

package com.neu.webdev2018summer1.thefoodexplorer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.neu.webdev2018summer1.thefoodexplorer.models.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

}

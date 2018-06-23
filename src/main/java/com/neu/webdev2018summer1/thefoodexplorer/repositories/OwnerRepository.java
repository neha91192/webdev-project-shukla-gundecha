package com.neu.webdev2018summer1.thefoodexplorer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.neu.webdev2018summer1.thefoodexplorer.models.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {

	@Query("SELECT o FROM Owner o JOIN o.restaurant r WHERE r.id =:rid")
	Iterable<Owner> findOwnerForRestaurant(@Param("rid") int rid);

}

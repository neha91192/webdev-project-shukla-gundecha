package com.neu.webdev2018summer1.thefoodexplorer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.neu.webdev2018summer1.thefoodexplorer.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	@Query("SELECT u FROM Customer u WHERE u.firstName LIKE CONCAT(:firstName,'%') OR u.lastName LIKE CONCAT(:lastName,'%')")
	Iterable<Customer> searchOR(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query("SELECT u FROM Customer u WHERE u.firstName LIKE CONCAT(:firstName,'%') AND u.lastName LIKE CONCAT(:lastName,'%')")
	Iterable<Customer> searchAND(@Param("firstName") String firstName, @Param("lastName") String lastName);

}

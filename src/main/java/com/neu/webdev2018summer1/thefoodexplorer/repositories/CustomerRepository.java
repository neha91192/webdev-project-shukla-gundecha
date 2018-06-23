package com.neu.webdev2018summer1.thefoodexplorer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.neu.webdev2018summer1.thefoodexplorer.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	@Query("SELECT u FROM User u WHERE u.firstName =:firstName OR u.lastName =:lastName")
	Iterable<Customer> search(@Param("firstName") String firstName, @Param("lastName") String lastName);
}

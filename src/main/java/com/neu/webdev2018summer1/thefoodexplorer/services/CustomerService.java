package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

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

import com.neu.webdev2018summer1.thefoodexplorer.models.Customer;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.CustomerRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@PostMapping("/api/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@GetMapping("/api/customer")
	public Iterable<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	@PutMapping("/api/customer/{customerId}")
	public Customer updateCustomer(@PathVariable("customerId") int id, @RequestBody Customer newCustomer) {
		Optional<Customer> data = customerRepository.findById(id);
		if (data.isPresent()) {
			Customer customer = data.get();
			customer.setFirstName(newCustomer.getFirstName());
			customer.setLastName(newCustomer.getLastName());
			customer.setPassword(newCustomer.getPassword());
			customer.setEmailId(newCustomer.getEmailId());
			customer.setDateOfBirth(newCustomer.getDateOfBirth());
			customer.setLocationArea(newCustomer.getLocationArea());
			customer.setMobileNumber(newCustomer.getMobileNumber());

			customerRepository.save(customer);
			return customer;
		} else
			return null;
	}

	@DeleteMapping("/api/customer/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") int id) {
		customerRepository.deleteById(id);
	}

	@GetMapping("/api/customer/profile")
	public Customer profile(HttpSession session) {
		return (Customer) session.getAttribute("currentUser");
	}
}

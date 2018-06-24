package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neu.webdev2018summer1.thefoodexplorer.models.Customer;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.CustomerRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/api/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		Customer newUser = customerRepository.save(customer);
		newUser.setPassword(null);
		return newUser;
	}

	@GetMapping("/api/customer")
	public Iterable<Customer> findAllCustomers(@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName) {
		Iterable<Customer> customers = null;
		if (firstName == null && lastName == null) {
			customers = customerRepository.findAll();
		} else if (firstName != null && lastName != null) {
			customers = customerRepository.searchAND(firstName, lastName);
		} else {
			customers = customerRepository.searchOR(firstName, lastName);
		}
		return customers;
	}

	@PutMapping("/api/customer/{customerId}")
	public Customer updateCustomer(@PathVariable("customerId") int id, @RequestBody Customer newCustomer) {
		Optional<Customer> data = customerRepository.findById(id);
		if (data.isPresent()) {
			Customer customer = data.get();
			customer.setFirstName(newCustomer.getFirstName());
			customer.setLastName(newCustomer.getLastName());
			customer.setEmailId(newCustomer.getEmailId());
			customer.setDateOfBirth(newCustomer.getDateOfBirth());
			customer.setBio(newCustomer.getBio());
			customer.setCity(newCustomer.getCity());
			customer.setCountry(newCustomer.getCountry());
			customer.setPincode(newCustomer.getPincode());
			customer.setState(newCustomer.getState());
			customer.setStreet(newCustomer.getStreet());
			customer.setMobileNumber(newCustomer.getMobileNumber());
			customerRepository.save(customer);
			customer.setPassword(null);
			return customer;
		} else
			return null;
	}

	@PutMapping("/api/customer/password/{customerId}")
	public Customer updateCustomerPassword(@PathVariable("customerId") int id, @RequestBody Customer newCustomer) {
		Optional<Customer> data = customerRepository.findById(id);
		if (data.isPresent()) {
			Customer customer = data.get();
			customer.setFirstName(newCustomer.getFirstName());
			customer.setLastName(newCustomer.getLastName());
			customer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
			customer.setEmailId(newCustomer.getEmailId());
			customer.setDateOfBirth(newCustomer.getDateOfBirth());
			customer.setBio(newCustomer.getBio());
			customer.setCity(newCustomer.getCity());
			customer.setCountry(newCustomer.getCountry());
			customer.setPincode(newCustomer.getPincode());
			customer.setState(newCustomer.getState());
			customer.setStreet(newCustomer.getStreet());
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
	public Customer profile(HttpServletRequest request, HttpSession session) {
		return (Customer) session.getAttribute("currentUser");
	}
}

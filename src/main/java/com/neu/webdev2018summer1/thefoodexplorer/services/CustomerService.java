package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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
import com.neu.webdev2018summer1.thefoodexplorer.models.User;
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
		if (firstName == "" && lastName == "") {
			customers = customerRepository.findAll();
		} else if (firstName != "" && lastName != "") {
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
			return data.get();
	}

	@DeleteMapping("/api/customer/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") int id) {
		customerRepository.deleteById(id);
	}

	@GetMapping("/api/customer/{userId}")
	public Customer findUser(HttpServletRequest request, HttpSession session, @PathVariable("userId") int userId) {
		Optional<Customer> userList = customerRepository.findById(userId);
		if (userList.isPresent()) {
			return userList.get();
		} else {
			return null;
		}
	}

	@PostMapping("/api/follow/{userId}")
	public Customer addFollower(@PathVariable("userId") int userId, HttpSession session, HttpServletResponse response) {
		Customer user = (Customer) session.getAttribute("currentUser");
		if (user != null && user.getUserId() != null) {
			Optional<Customer> follower = customerRepository.findById(user.getUserId());
			Optional<Customer> userToFollow = customerRepository.findById(userId);
			if (follower.isPresent()) {
				userToFollow.get().getFollowers().add(follower.get());
//				follower.get().getFollowing().add(userToFollow.get());
				customerRepository.save(userToFollow.get());
				return follower.get();
			}
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return user;

	}

	@GetMapping("/api/follower")
	public List<Customer> findFollowers(HttpSession session, HttpServletResponse response) {
		User user = (User) session.getAttribute("currentUser");
		if (user != null && user.getUserId() != null) {
			Optional<Customer> customer = customerRepository.findById(user.getUserId());
			if (customer.isPresent()) {
				return customer.get().getFollowers();
			}
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return null;
	}

	@GetMapping("/api/following")
	public List<Customer> findFollowing(HttpSession session, HttpServletResponse response) {
		Customer follower = (Customer) session.getAttribute("currentUser");
		if (follower != null && follower.getUserId() != null) {
			Optional<Customer> followUser = customerRepository.findById(follower.getUserId());
			if (followUser.isPresent()) {
				return followUser.get().getFollowing();
			}
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return null;
	}

	@PutMapping("/api/follow/{userId}")
	public Customer removeFollower(@PathVariable("userId") int userId, HttpSession session,
			HttpServletResponse response) {
		Customer user = (Customer) session.getAttribute("currentUser");
		if (user != null && user.getUserId() != null) {
			Optional<Customer> follower = customerRepository.findById(user.getUserId());
			Optional<Customer> userToUnFollow = customerRepository.findById(userId);
			if (follower.isPresent()) {
				userToUnFollow.get().getFollowers().remove(follower.get());
				customerRepository.save(userToUnFollow.get());
				return follower.get();
			}
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return user;
	}

}

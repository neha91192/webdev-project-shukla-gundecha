package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neu.webdev2018summer1.thefoodexplorer.models.User;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.UserRepository;

@RestController
public class UserService {

	@Autowired
	UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/api/profile")
	public User profile(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		if (user != null) {
			Optional<User> userList = repository.findById(user.getUserId());
			if (userList.isPresent()) {
				return userList.get();
			}
		}
		return user;
	}

	public User profile(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		User user = (User) session.getAttribute("currentUser");
		if (user != null && user.getUserId() != null) {
			Optional<User> userList = repository.findById(user.getUserId());
			if (userList.isPresent()) {
				return userList.get();
			}
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return user;
	}
	
	@GetMapping("api/user")
	public Iterable<User> findAllUsers() {
		return repository.findAll();
	}
	
	@PostMapping("api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user); //insert data into the table in database
	}
	
	@DeleteMapping("api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PutMapping("api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setMobileNumber(newUser.getMobileNumber());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setBio(newUser.getBio());
			user.setCity(newUser.getCity());
			user.setCountry(newUser.getCountry());
			user.setEmailId(newUser.getEmailId());
			user.setPassword(passwordEncoder.encode(newUser.getPassword()));
			user.setPincode(newUser.getPincode());
			user.setStreet(newUser.getStreet());
			user.setUserType(newUser.getUserType());
			
			repository.save(user);	
			return user;
		}	
		else 
			return null;
	}
	
	@GetMapping("api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId){
    		Optional<User> data = repository.findById(userId);
    		System.out.println("user:"+data);
    		if(data.isPresent())
    			return data.get();
    		else 
    			return null;
    }
	
}

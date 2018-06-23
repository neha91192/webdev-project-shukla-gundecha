package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;


import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neu.webdev2018summer1.thefoodexplorer.models.User;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.UserRepository;

@RestController
public class UserService {
	
	@Autowired UserRepository repository;
	
	@GetMapping("/api/profile")
	public User profile(HttpServletRequest request, HttpSession session) {
			User user = (User)session.getAttribute("currentUser");
			if(user!=null) {
				Optional<User> userList = repository.findById(user.getUserId());
			if(userList.isPresent()) {
				return userList.get();
			}
			else {
				return null;
			}}
			return user;
	}
}

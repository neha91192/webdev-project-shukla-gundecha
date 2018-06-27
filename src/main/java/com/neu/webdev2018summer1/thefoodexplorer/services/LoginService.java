package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.webdev2018summer1.thefoodexplorer.models.User;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		User fetchedUser = null;
		Iterable<User> result = userRepository.findUserByUsername(user.getUsername());

		for (User userval : result) {
			if (passwordEncoder.matches(user.getPassword(), userval.getPassword())) {
				fetchedUser = userval;
				session.setAttribute("currentUser", fetchedUser);
				return fetchedUser;
			}

		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return fetchedUser;
	}

	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@PostMapping("/api/socialLogin")
	public User socialLogin(@RequestBody User user, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		User newUser = null;

		Optional<User> result = userRepository.findById(user.getUserId());
		if (result.isPresent()) {
			session.setAttribute("currentUser", user);
			return user;
		} else {
			newUser = userRepository.save(user);
			return newUser;
		}

	}

}

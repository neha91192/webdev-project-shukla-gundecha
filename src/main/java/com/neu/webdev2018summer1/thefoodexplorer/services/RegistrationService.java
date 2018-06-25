package com.neu.webdev2018summer1.thefoodexplorer.services;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.webdev2018summer1.thefoodexplorer.enumerations.UserType;
import com.neu.webdev2018summer1.thefoodexplorer.models.Blogger;
import com.neu.webdev2018summer1.thefoodexplorer.models.Customer;
import com.neu.webdev2018summer1.thefoodexplorer.models.Owner;
import com.neu.webdev2018summer1.thefoodexplorer.models.Restaurant;
import com.neu.webdev2018summer1.thefoodexplorer.models.User;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.BloggerRepository;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.CustomerRepository;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.OwnerRepository;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegistrationService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BloggerRepository bloggerRepository;
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session, HttpServletResponse response) {
		User newUser = new User();
		Iterable<User> result = userRepository.findUserByUsername(user.getUsername());
		if (result == null) {

			// add switch case later

			if (user.getUserType().equals(UserType.Customer)) {
				Customer customer = new Customer();
				customer.setFirstName(user.getFirstName());
				customer.setUsername(user.getUsername());
				customer.setPassword(passwordEncoder.encode(user.getPassword()));
				customer.setUserType(UserType.Customer);

				newUser = customerRepository.save(customer);

			}

			if (user.getUserType().equals(UserType.Blogger)) {
				Blogger blogger = new Blogger();
				blogger.setFirstName(user.getFirstName());
				blogger.setUsername(user.getUsername());
				blogger.setPassword(passwordEncoder.encode(user.getPassword()));
				blogger.setUserType(UserType.Blogger);

				newUser = bloggerRepository.save(blogger);

			}

			if (user.getUserType().equals(UserType.Owner)) {
				Owner owner = new Owner();
				owner.setFirstName(user.getFirstName());
				owner.setUsername(user.getUsername());
				owner.setPassword(passwordEncoder.encode(user.getPassword()));
				owner.setUserType(UserType.Owner);
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(owner.getRestaurant().getRestaurantId());
				owner.setRestaurant(restaurant);

				newUser = ownerRepository.save(owner);

			}
			session.setAttribute("currentUser", newUser);
		} else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		return newUser;
	}

}

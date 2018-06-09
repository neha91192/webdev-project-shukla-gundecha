package com.example.poc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST class exposed to perform User related operations
 * 
 * @author nehashukla
 *
 */
@RestController
public class UserService {

	/**
	 * Returns all the users present in the repository.
	 * 
	 * @return List of User objects containing user details
	 */
	@Secured({ "ROLE_USER" })
	@GetMapping("/api/user")
	public List<User> findAllUsers(@RequestParam(name = "username", required = false) String username,
			HttpServletResponse response) {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setFirstName("Neha");
		user.setLastName("Shukla");
		user.setUsername("neha");
		user.setPassword("neha");
		userList.add(user);
		return userList;
	}

	/**
	 * Returns all the users present in the repository.
	 * 
	 * @return List of User objects containing user details
	 */
	@GetMapping("/api/course")
	public List<Course> findAllCourses(@RequestParam(name = "name", required = false) String name,
			HttpServletResponse response) {
		List<Course> courseList = new ArrayList<Course>();
		Course course = new Course();
		course.setName("course1");
		course.setNumber("123");
		courseList.add(course);
		return courseList;
	}

}

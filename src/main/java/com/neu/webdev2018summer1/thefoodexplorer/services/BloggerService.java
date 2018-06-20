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

import com.neu.webdev2018summer1.thefoodexplorer.models.Blogger;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.BloggerRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BloggerService {
	@Autowired
	BloggerRepository bloggerRepository;

	@PostMapping("/api/blogger")
	public Blogger createBlogger(@RequestBody Blogger blogger) {
		return bloggerRepository.save(blogger);
	}

	@GetMapping("/api/blogger")
	public Iterable<Blogger> findAllBloggers() {
		return bloggerRepository.findAll();
	}

	@PutMapping("api/blogger/{bloggerId}")
	public Blogger updateBlogger(@PathVariable("bloggerId") int id, @RequestBody Blogger newBlogger) {
		Optional<Blogger> data = bloggerRepository.findById(id);
		if (data.isPresent()) {
			Blogger blogger = data.get();
			blogger.setFirstName(newBlogger.getFirstName());
			blogger.setLastName(newBlogger.getLastName());
			blogger.setPassword(newBlogger.getPassword());
			blogger.setEmailId(newBlogger.getEmailId());
			blogger.setDateOfBirth(newBlogger.getDateOfBirth());
			blogger.setMobileNumber(newBlogger.getMobileNumber());

			bloggerRepository.save(blogger);
			return blogger;
		} else
			return null;
	}

	@DeleteMapping("/api/blogger/{bloggerId}")
	public void deleteBlogger(@PathVariable("bloggerId") int id) {
		bloggerRepository.deleteById(id);
	}

	@GetMapping("/api/blogger/profile")
	public Blogger profile(HttpSession session) {
		return (Blogger) session.getAttribute("currentUser");
	}
}

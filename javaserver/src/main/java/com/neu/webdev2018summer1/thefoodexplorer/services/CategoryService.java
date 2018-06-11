package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.neu.webdev2018summer1.thefoodexplorer.models.Category;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.CategoryRepository;

public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	/**
	 * Creates category object in database
	 * 
	 * @param reservation
	 * @return
	 */
	@PostMapping("/api/category")
	public Category createCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	/**
	 * Returns all category in database
	 * 
	 * @return
	 */
	@GetMapping("/api/category")
	public Iterable<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * Returns category details by Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/category/{categoryId}")
	public Category readCategory(@PathVariable("categoryId") int id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return category.get();
		} else {
			return null;
		}
	}

	/**
	 * Updates category object in the database
	 * 
	 * @param id
	 * @param category
	 * @return
	 */
	@PutMapping("api/category/{categoryId}")
	public Category updateCategory(@PathVariable("categoryId") int id, @RequestBody Category category) {

		Optional<Category> data = categoryRepository.findById(id);
		if (data.isPresent()) {
			Category existingCategory = data.get();
			existingCategory.setName(category.getName());
			existingCategory.setRestaurants(category.getRestaurants());

			categoryRepository.save(existingCategory);
			return existingCategory;
		} else
			return null;
	}

	/**
	 * Deletes category by id
	 * 
	 * @param id
	 */
	@DeleteMapping("/api/category/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") int id) {
		categoryRepository.deleteById(id);
	}
}

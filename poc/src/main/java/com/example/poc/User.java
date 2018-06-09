package com.example.poc;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Plain old Java Entity class to represent User data.
 *
 * @author nehashukla
 *
 */

public class User {

	/**
	 * System Generated ID for User
	 */
	private int id;
	/**
	 * login field for user
	 */
	private String username;
	/**
	 * Password field for user
	 */
	private String password;
	/**
	 * Field to store First Name of user
	 */
	private String firstName;
	/**
	 * Field to store Last Name of user
	 */
	private String lastName;
	/**
	 * Stores Phone Number of user
	 */
	private String phone;
	/**
	 * Stores email id of user
	 */
	private String email;
	/**
	 * User can be either student or faculty.
	 */
	private String role;
	/**
	 * Stores date of birth of user
	 */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfBirth;

	/**
	 * Returns ID for user
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the value of ID
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Fetches login name of user
	 *
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets login name of user
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Fetches password of user
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password of user
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns first name of user
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name of user
	 *
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Fetches Last name of the user
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name of the user
	 *
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Fetches phone number of the user
	 *
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets phone number of the user
	 *
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Returns Email id of the user
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets Email id of the user
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Fetches Role of the user
	 *
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets role of the user
	 *
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Fetches date of birth of the user
	 *
	 * @return dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets date of birth of the user
	 *
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

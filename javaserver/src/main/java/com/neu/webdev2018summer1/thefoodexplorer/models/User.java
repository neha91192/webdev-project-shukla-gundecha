package com.neu.webdev2018summer1.thefoodexplorer.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.neu.webdev2018summer1.thefoodexplorer.enumerations.UserType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String emailId;
	private String mobileNumber;
	private UserType userType;
	/**
	 * One user can request for multiple reservations
	 */
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Reservation> reservations;
	/**
	 * One user write multiple reviews
	 */
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Review> reviews;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * Returns reservation list
	 * 
	 * @return
	 */
	public List<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * Sets reservation list
	 * 
	 * @param reservations
	 */
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	/**
	 * Gets list of reviews written by this user
	 * 
	 * @return
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * Sets list of reviews written by this user
	 * 
	 * @param reviews
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}

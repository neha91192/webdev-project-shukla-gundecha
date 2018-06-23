package com.neu.webdev2018summer1.thefoodexplorer.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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

import com.neu.webdev2018summer1.thefoodexplorer.enumerations.UserType;
import com.neu.webdev2018summer1.thefoodexplorer.models.Owner;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.OwnerRepository;
import com.neu.webdev2018summer1.thefoodexplorer.repositories.RestaurantRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OwnerService {
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	RestaurantRepository restaurantRepository;

	@PostMapping("/api/owner")
	public Owner createOwner(@RequestBody Owner owner, HttpServletResponse response, HttpSession session) {
		Owner newOwnerObj = null;
		if (owner.getRestaurant() != null && owner.getRestaurant().getRestaurantId() != 0) {
			Iterable<Owner> result = ownerRepository.findOwnerForRestaurant(owner.getRestaurant().getRestaurantId());
			for (Owner ownerData : result) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return null;
			}
			owner.setUserType(UserType.Owner);
			newOwnerObj = ownerRepository.save(owner);
			session.setAttribute("currentUser", newOwnerObj);

		}
		return newOwnerObj;
	}

	@GetMapping("/api/owner")
	public Iterable<Owner> findAllOwners() {
		return ownerRepository.findAll();
	}

	@PutMapping("api/owner/{ownerId}")
	public Owner updateOwner(@PathVariable("ownerId") int id, @RequestBody Owner newOwner) {
		Optional<Owner> data = ownerRepository.findById(id);
		if (data.isPresent()) {
			Owner owner = data.get();
			owner.setFirstName(newOwner.getFirstName());
			owner.setLastName(newOwner.getLastName());
			owner.setPassword(newOwner.getPassword());
			owner.setEmailId(newOwner.getEmailId());
			owner.setDateOfBirth(newOwner.getDateOfBirth());
			owner.setMobileNumber(newOwner.getMobileNumber());
			owner.setTerm(newOwner.getTerm());

			ownerRepository.save(owner);
			return owner;
		} else
			return null;
	}

	@DeleteMapping("/api/owner/{ownerId}")
	public void deleteOwner(@PathVariable("ownerId") int id) {
		ownerRepository.deleteById(id);
	}

	@GetMapping("/api/owner/profile")
	public Owner profile(HttpSession session) {
		return (Owner) session.getAttribute("currentUser");
	}

}

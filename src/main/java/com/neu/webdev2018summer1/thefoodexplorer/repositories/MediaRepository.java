package com.neu.webdev2018summer1.thefoodexplorer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.neu.webdev2018summer1.thefoodexplorer.models.Media;

public interface MediaRepository extends CrudRepository<Media, Integer> {

	@Query("SELECT m FROM Media m JOIN m.restaurant r WHERE r.id =:rid AND m.imageType=:mediaType ORDER BY m.imageOrder")
	Iterable<Media> findMediaForRestaurant(@Param("rid") int rid, @Param("mediaType") String mediaType);

}

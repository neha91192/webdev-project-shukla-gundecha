package com.neu.webdev2018summer1.thefoodexplorer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.neu.webdev2018summer1.thefoodexplorer.models.Blogger;

public interface BloggerRepository extends CrudRepository<Blogger, Integer> {

}

package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	  User findByEmailAndPassword(String email, String password);
	  
	    User findByUsername(String username);
	    

}

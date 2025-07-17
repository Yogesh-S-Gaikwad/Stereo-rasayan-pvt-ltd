package com.project.repo;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	boolean existsByEmail(String email);

	  User findByEmailAndPassword(String email, String password);
	  
	    User findByEmail(String email);
	    
	    Page<User> findByRole(String role, PageRequest pageable);

}

package com.project.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.model.User;


public interface userService {

	  void save(User user);
	  boolean emailExists(String email);
	  User findByEmail(String email);
	  Page<User> findByRole(String role, PageRequest pageable);
	  
}

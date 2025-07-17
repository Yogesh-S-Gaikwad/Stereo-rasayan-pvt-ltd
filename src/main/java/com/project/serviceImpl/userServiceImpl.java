package com.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.repo.UserRepo;
import com.project.service.userService;

@Service
public class userServiceImpl implements userService{

	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public void save(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		userRepository.save(user);
		
	}

	@Override
	public boolean emailExists(String email) {
		  return userRepository.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	@Override
	public Page<User> findByRole(String role, PageRequest pageable) {
		// TODO Auto-generated method stub
		return userRepository.findByRole(role, pageable);
	}
	

}

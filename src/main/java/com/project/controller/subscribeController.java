package com.project.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Subscriber;
import com.project.repo.SubscriberRepo;

@Controller
public class subscribeController {
	
	@Autowired
	private SubscriberRepo subscriberRepo;
	
	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribe(@RequestParam("email") String email) {
	    if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
	        return ResponseEntity.badRequest().body("Invalid email");
	    }

	    Subscriber sub = new Subscriber();
	    sub.setEmail(email);
	    sub.setSubscibeAt(LocalDateTime.now());
	    subscriberRepo.save(sub);

	    return ResponseEntity.ok("Subscribed successfully");
	}

}

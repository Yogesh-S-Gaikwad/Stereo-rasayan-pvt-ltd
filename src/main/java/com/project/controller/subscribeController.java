package com.project.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Products;
import com.project.model.Subscriber;
import com.project.repo.SubscriberRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

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

	@GetMapping("/subscribers")
	public String showSubscribers(
	        @RequestParam(defaultValue = "0") int page, 
	        Model model) {

	    Pageable pageable = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC, "id")); 
	    Page<Subscriber> subscriberPage = subscriberRepo.findAll(pageable);

	    model.addAttribute("subscribers", subscriberPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", subscriberPage.getTotalPages());

	    return "showSubscribers"; 
	}
}

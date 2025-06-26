package com.project.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Subscriber;
import com.project.repo.SubscriberRepo;

@Controller
public class homeController {
	
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	 // Admin Dashboard
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admin-dashboard"; // Create this JSP/HTML
    }

    // User Home Page
    @GetMapping("/user/home")
    public String showUserHome() {
        return "user-home"; // Create this JSP/HTML
    }
	
	
}

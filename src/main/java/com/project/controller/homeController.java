package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
	
	
	@GetMapping("/")
    public String home() {
        return "index"; 
    }
	@GetMapping("/aboutUs")
	public String aboutUsPage() {
		return "aboutUs";
	}
	@GetMapping("/contact")
	public String contactPage() {
		return "contact";
	}
	
	
	
}

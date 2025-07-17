package com.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.model.User;
import com.project.repo.UserRepo;

@Controller
public class cartController {
	
    @Autowired
    private UserRepo userRepo;
    
    @GetMapping("/cart")
    public String cartPage() {
    
        return "cart"; 
    }
    
    @PostMapping
    public String addToCart() {
    	
    	return"cart";
    }
    @ModelAttribute
	public void getUser(Principal p, Model m) {
		if(p!=null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user" , user);
		}
	}
}

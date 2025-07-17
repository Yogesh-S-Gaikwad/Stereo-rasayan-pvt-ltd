package com.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.model.Products;
import com.project.model.User;
import com.project.repo.ProductRepo;
import com.project.repo.UserRepo;
import com.project.service.userService;

import org.springframework.ui.Model;

@Controller
public class homeController {
	
    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private UserRepo userRepo;

	@GetMapping("/")
	public String home(Model model) {
		 List<Products> products = productRepo.findAll();
		
	        model.addAttribute("products", products);
	     
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
	
	@GetMapping("/signup")
	public String signup(){
		return "signup";
	}
	
	@GetMapping("/verifyOTP")
	public String verifyOTP(){
		return "verifyOTP";
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

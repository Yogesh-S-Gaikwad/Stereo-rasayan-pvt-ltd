package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.User;
import com.project.repo.UserRepo;

@Controller
public class LoginController {

	 @Autowired
	    private UserRepo userRepository;

	    @PostMapping("/login")
	    public String login(@RequestParam String username,
	                        @RequestParam String password,
	                        RedirectAttributes redirectAttributes) {
	        User user = userRepository.findByUsernameAndPassword(username, password);

	        if (user != null) {
	            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
	                redirectAttributes.addAttribute("success", true);
	                return "redirect:/dashboard.html";
	            } else {
	                redirectAttributes.addAttribute("success", true);
	                return "redirect:/";
	            }
	        } else {
	            redirectAttributes.addAttribute("error", true);
	            return "redirect:/";
	        }
	    }

	    @PostMapping("/signup")
	    public String signup(@RequestParam String username,
	                         @RequestParam String password,
	                         RedirectAttributes redirectAttributes) {
	        if (userRepository.findByUsername(username) != null) {
	            redirectAttributes.addFlashAttribute("error", "Username already exists");
	            return "redirect:/";
	        }

	        User newUser = new User();
	        newUser.setUsername(username);
	        newUser.setPassword(password);
	        newUser.setRole("USER");
	        userRepository.save(newUser);

	        redirectAttributes.addFlashAttribute("message", "Signup successful! Please log in.");
	        return "redirect:/";
	    }}
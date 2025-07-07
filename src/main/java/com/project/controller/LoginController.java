package com.project.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.User;
import com.project.repo.UserRepo;
import com.project.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	 @Autowired
	    private UserRepo userRepository;
	 
	 @Autowired
	 private EmailService emailService;

	    @PostMapping("/login")
	    public String login(@RequestParam String email,
	                        @RequestParam String password,
	                        RedirectAttributes redirectAttributes) {
	        User user = userRepository.findByEmailAndPassword(email, password);

	        if (user != null) {
	            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
	                redirectAttributes.addAttribute("success", "admin");
	                return "redirect:/dashboard";
	            } else {
	                redirectAttributes.addAttribute("success", "user");
	                return "redirect:/user";
	            }
	        } else {
	            redirectAttributes.addAttribute("success", "error");
	            return "redirect:/";
	        }
	    }

	    @GetMapping("/send-email-otp")
	    @ResponseBody
	    public String sendEmailOtp(@RequestParam String email, HttpSession session) {
	        String otp = String.valueOf(new Random().nextInt(899999) + 100000);
	        session.setAttribute("otp", otp);
	        session.setAttribute("email", email);
	        emailService.sendOtp(email, otp);
	        return "OTP sent";
	    }

	    @PostMapping("/verify-email-otp")
	    public String verifyOtp(@RequestParam String username,
	                            @RequestParam String password,
	                            @RequestParam String email,
	                            @RequestParam String otp,
	                            HttpSession session,
	                            RedirectAttributes redirectAttributes) {

	        String sessionOtp = (String) session.getAttribute("otp");
	        String sessionEmail = (String) session.getAttribute("email");

	        if (otp.equals(sessionOtp) && email.equals(sessionEmail)) {
	            if (userRepository.findByUsername(username) != null) {
	                redirectAttributes.addFlashAttribute("error", "Username already exists");
	                return "redirect:/";
	            }

	            User newUser = new User();
	            newUser.setUsername(username);
	            newUser.setPassword(password);
	            newUser.setRole("USER");
	            newUser.setEmail(email);
	            userRepository.save(newUser);
	            redirectAttributes.addFlashAttribute("message", "Signup successful!");
	        } else {
	            redirectAttributes.addFlashAttribute("error", "Invalid OTP");
	        }

	        return "redirect:/";
	    }
}
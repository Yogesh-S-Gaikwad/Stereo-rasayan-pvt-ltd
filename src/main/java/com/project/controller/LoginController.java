package com.project.controller;

import java.security.Principal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.User;
import com.project.repo.UserRepo;
import com.project.service.EmailService;
import com.project.service.userService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	 @Autowired
	    private UserRepo userRepository;
	 
	 @Autowired
	 private EmailService emailService;
	 
	 @Autowired
	 private userService userService;
	 
	 
	 @PostMapping("/register")
	 public String saveUser(@ModelAttribute("user") User user,
	                        BindingResult result,
	                        Model model) {

	     if (userService.emailExists(user.getEmail())) {
	         result.rejectValue("email", null, "Email already registered");
	     }

	     if (result.hasErrors()) {
	    	 model.addAttribute("error", "Email already registered");
	         return "signup";
	     }

	     userService.save(user);

	     model.addAttribute("message", "Registered successfully!");
	     return "signup";
	 }
	 @GetMapping("/login")
		public String loginpage(){
			return "login";
		}
	 @GetMapping("/postLogin")
	 public String postLogin(Authentication authentication) {
	     if (authentication.getAuthorities().stream()
	             .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
	         return "redirect:/admin/dashboard";
	     }
	     return "redirect:/";
	 }
	 @GetMapping("/logout")
	 public String logout() {
		 return "login";
	 }
//	 @GetMapping("/login")
//	 public String loginPage(@RequestParam(value = "error", required = false) String error,
//	                         Model model) {
//	     if (error != null) {
//	         model.addAttribute("loginError", "Invalid email or password");
//	     }
//	     return "login";
//	 }

		@ModelAttribute
		public void getUser(Principal p, Model m) {
			if(p!=null) {
				String email = p.getName();
				User user = userRepository.findByEmail(email);
				m.addAttribute("user" , user);
			}
		}
	  
	 
//	    @PostMapping("/login")
//	    public String login(@RequestParam String email,
//	                        @RequestParam String password,
//	                        RedirectAttributes redirectAttributes) {
//	        User user = userRepository.findByEmailAndPassword(email, password);
//
//	        if (user != null) {
//	        	
//	            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
//	                redirectAttributes.addAttribute("success", "admin");
//	                return "redirect:/admin/dashboard";
//	            } else {
//	                redirectAttributes.addAttribute("success", "user");
//	                return "redirect:/";
//	            }
//	        } else {
//	            redirectAttributes.addAttribute("success", "error");
//	            return "redirect:/";
//	        }
//	    }
//
//	    @GetMapping("/send-email-otp")
//	    public ResponseEntity<String> sendEmailOtp(@RequestParam String email, HttpSession session) {
//	        try {
//	            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
//	            session.setAttribute("otp", otp);
//	            session.setAttribute("email", email);
//	            emailService.sendOtp(email, otp);
//	            return ResponseEntity.ok("OTP sent");
//	        } catch (Exception e) {
//	            return ResponseEntity.status(500).body("Failed to send OTP");
//	        }
//	    }
//
//	    @PostMapping("/verify-email-otp")
//	    public String verifyOtp(@RequestParam String username,
//	                            @RequestParam String password,
//	                            @RequestParam String email,
//	                            @RequestParam String otp,
//	                            HttpSession session,
//	                            RedirectAttributes redirectAttributes) {
//
//	        String sessionOtp = (String) session.getAttribute("otp");
//	        String sessionEmail = (String) session.getAttribute("email");
//
//	        if (otp.equals(sessionOtp) && email.equals(sessionEmail)) {
//	            if (userRepository.findByEmail(email) != null) {
//	                redirectAttributes.addFlashAttribute("error", "Username already exists"); 
//	                return "redirect:/"; 
//	            }
//
//	            User newUser = new User();
//	            newUser.setUsername(username);
//	            newUser.setPassword(password);
//	            newUser.setRole("USER");
//	            newUser.setEmail(email);
//	            userRepository.save(newUser);
//	            redirectAttributes.addFlashAttribute("message", "Signup successful!");
//	        } else {
//	            redirectAttributes.addFlashAttribute("error", "Invalid OTP");
//	        }
//
//	        return "redirect:/";
//	    }
}
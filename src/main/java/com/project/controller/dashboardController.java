package com.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Products;
import com.project.model.User;
import com.project.repo.ProductRepo;
import com.project.repo.UserRepo;
import com.project.service.userService;

@Controller
public class dashboardController {
	  @Autowired
	    private ProductRepo productRepo;
	    @Autowired
	    private UserRepo userRepo;
	    @Autowired
	    private userService userService;
	  
	  @GetMapping("/admin/dashboard")
	    public String viewDashboard(Model model) {
	        List<Products> products = productRepo.findAll();
	        model.addAttribute("products", products);
	        return "admin/dashboard"; 
	    }
		@GetMapping("/admin/addProduct")
	    public String addProduct() {
	        return "admin/addProduct"; 
	    }
	
		@GetMapping("/admin/users")
		public String getAllUses(
		        @RequestParam(defaultValue = "0") int page,
		        Model m) {

		    PageRequest pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
		    Page<User> userPage = userService.findByRole("USER", pageable);

		    m.addAttribute("users", userPage.getContent());
		    m.addAttribute("currentPage", page);
		    m.addAttribute("totalPages", userPage.getTotalPages());

		    return "admin/users";
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

package com.project.controller;

import com.project.model.Products;
import com.project.model.User;
import com.project.repo.ProductRepo;
import com.project.repo.UserRepo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private UserRepo userRepo;

  
    @PostMapping("/save-product")
    public String saveProduct(
            @RequestParam("name") String name,
            @RequestParam("casNo") String casNo,
            @RequestParam("packingSize") String packingSize,
            @RequestParam("rate") String rate,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam(value="bestseller", required=false) String bestSeller
    ) throws IOException {

        if (description != null && description.length() > 255) {
            description = description.substring(0, 255);
        }
        
        Products product = new Products();
        product.setName(name);
        product.setCasNo(casNo);
        product.setPackingSize(packingSize);
        product.setRate(rate);
        product.setDescription(description);
        product.setCategory(category);
        
        product.setBestSeller("yes".equals(bestSeller) ? "yes" : null);


        if (!imageFile.isEmpty()) {
            product.setImage(imageFile.getBytes());
        }

        if (!pdfFile.isEmpty()) {
            product.setProductPdf(pdfFile.getBytes());
        }

        productRepo.save(product);

        return "redirect:/dashboard"; 
    }


    @GetMapping("/image/{id}")
    public void showImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Products product = productRepo.findById(id).orElse(null);
        if (product != null && product.getImage() != null) {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(product.getImage());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/pdf/{id}")
    public void showPdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Products product = productRepo.findById(id).orElse(null);
        if (product != null && product.getProductPdf() != null) {
            response.setContentType("application/pdf");
            response.getOutputStream().write(product.getProductPdf());
            response.getOutputStream().close();
        }
    }
    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
    	productRepo.deleteById(id);
        return "redirect:/dashboard";
    }
    
    @GetMapping("/edit-product/{id}")
    public String editProductForm(@PathVariable Long id, String source,
            Model model) {
    		Products product = productRepo.findById(id).orElseThrow();
    		 model.addAttribute("product", product);
    		    model.addAttribute("sourcePage", source);
    		    
        return "EditProduct";
    }
    @PostMapping("/update-product")
    public String updateProduct(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("casNo") String casNo,
            @RequestParam("packingSize") String packingSize,
            @RequestParam("rate") String rate,
            @RequestParam("description") String description,
            @RequestParam(value = "bestSeller", required = false) String bestSeller,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam("sourcePage") String sourcePage
    ) throws IOException {

     
        Products product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

     
        product.setName(name);
        product.setCategory(category);
        product.setCasNo(casNo);
        product.setPackingSize(packingSize);
        product.setRate(rate);
        product.setDescription(description);
        product.setBestSeller("yes".equals(bestSeller) ? "yes" : null);


        if (!imageFile.isEmpty()) {
            product.setImage(imageFile.getBytes());
        }

        if (!pdfFile.isEmpty()) {
            product.setProductPdf(pdfFile.getBytes());
        }

        productRepo.save(product);
       

        return ("redirect:/" + sourcePage);
    }

    
    @GetMapping("/product")
    public String showAllProducts(Model model) {
        List<Products> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "product"; 
    }
    
    @GetMapping("/admin/best-selling-product")
    public String showBestSellingProducts(Model model) {
        List<Products> products = productRepo.findByBestSeller("yes");
        if (products.isEmpty()) {
            model.addAttribute("message", "No best-selling products found.");
        }
        model.addAttribute("products", products);
        return "admin/bestSeller"; 
    }
    
    @GetMapping("/productDetails/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Products product = productRepo.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "productDetails";
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
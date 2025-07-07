package com.project.controller;

import com.project.model.Products;
import com.project.repo.ProductRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        List<Products> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "dashboard"; 
    }
    @PostMapping("/save-product")
    public String saveProduct(
            @RequestParam("name") String name,
            @RequestParam("casNo") String casNo,
            @RequestParam("packingSize") String packingSize,
            @RequestParam("rate") String rate,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("pdfFile") MultipartFile pdfFile
    ) throws IOException {

        Products product = new Products();
        product.setName(name);
        product.setCasNo(casNo);
        product.setPackingSize(packingSize);
        product.setRate(rate);
        product.setDescription(description);
        product.setCategory(category);

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
    
    @GetMapping("/product")
    public String showAllProducts(Model model) {
        List<Products> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "product"; 
    }

    @GetMapping("/productDetails/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Products product = productRepo.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "productDetails";
    }
    
}
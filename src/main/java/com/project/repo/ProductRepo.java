package com.project.repo;

import com.project.model.Products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products, Long> {
	
	List<Products> findByBestSeller(String bestSeller);
	

	
}
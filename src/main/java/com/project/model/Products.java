package com.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String casNo;
    private String packingSize;
    private String rate;
    private String description;
    private String category;
    private String bestSeller;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Lob
    @Column(name = "product_pdf", columnDefinition = "LONGBLOB")
    private byte[] productPdf;

    
    // Getters and setters...
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCasNo() {
		return casNo;
	}

	public void setCasNo(String casNo) {
		this.casNo = casNo;
	}

	public String getPackingSize() {
		return packingSize;
	}

	public void setPackingSize(String packingSize) {
		this.packingSize = packingSize;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getProductPdf() {
		return productPdf;
	}

	public void setProductPdf(byte[] productPdf) {
		this.productPdf = productPdf;
	}

	public String getBestSeller() {
		return bestSeller;
	}

	public void setBestSeller(String bestSeller) {
		this.bestSeller = bestSeller;
	}
	
	
 
    
    
}

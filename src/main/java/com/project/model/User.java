package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String mobileNumber;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String password;
    private String role="USER";
    private boolean isEnabled = true;
     
    

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public boolean isEnabled() {
	    return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
	    this.isEnabled = isEnabled;
	}
	public User(Long id, String username, String email, String mobileNumber, String address, String city, String state,
			String pincode, String password, String role, boolean isEnabled) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.password = password;
		this.role = role;
		this.isEnabled = isEnabled;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

package com.project.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subscriber {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private LocalDateTime subscibeAt = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getSubscibeAt() {
		return subscibeAt;
	}
	public void setSubscibeAt(LocalDateTime subscibeAt) {
		this.subscibeAt = subscibeAt;
	}
	public Subscriber(int id, String email, LocalDateTime subscibeAt) {
		super();
		this.id = id;
		this.email = email;
		this.subscibeAt = subscibeAt;
	}
	public Subscriber() {
		
	}
	
	
}

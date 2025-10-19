package com.example.allergy.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String category;
	private String name;
	
	@ElementCollection
	private List<String>allergies = new ArrayList<>();
	
	// getter / setter
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id;}
	
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies; }
}
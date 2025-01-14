package com.e_commerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="category")
public class Category {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long categoryId;
	    @Column(name="CatName")
	    private String name;
	    @Column(name="CatDescription")
	    private String description;
	    @Column(name="ProductCategoryId")
	    private Long productCategoryId;

	    
	    
	    public Category() {
			super();
			// TODO Auto-generated constructor stub
		}

	    
		public Category(String name, String description, Long productCategoryId) {
			super();
			this.name = name;
			this.description = description;
			this.productCategoryId = productCategoryId;
		}


		// Getters and Setters
	    public Long getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(Long categoryId) {
	        this.categoryId = categoryId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Long getProductCategoryId() {
	        return productCategoryId;
	    }

	    public void setProductCategoryId(Long productCategoryId) {
	        this.productCategoryId = productCategoryId;
	    }
	}



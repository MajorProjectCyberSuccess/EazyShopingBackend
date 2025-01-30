package com.eazyapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;

	@Column(nullable = false)
	private String name;

	@Column(length = 500)
	private String productDetails;

	@Column(nullable = false)
	private long categoryId;

	@Column(nullable = false)
	private double price;

	@Column
	private String productImage;

//	@ManyToMany(mappedBy = "products")
//	private Set<Cart> carts;
//
//	@ManyToMany(mappedBy = "products")
//	private Set<Order> orders;
}
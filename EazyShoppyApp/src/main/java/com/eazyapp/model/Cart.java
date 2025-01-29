package com.eazyapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;

	@Column(nullable = false)
	private long itemId;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private double totalAmount;

	@ManyToMany
	@JoinTable(
			name = "cart_product",
			joinColumns = @JoinColumn(name = "cart_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private Set<Product> products;
}
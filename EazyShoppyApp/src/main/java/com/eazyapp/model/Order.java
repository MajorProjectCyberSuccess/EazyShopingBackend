package com.eazyapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private LocalDate orderDate;

	@Column(nullable = false)
	private Double totalAmount;

	@Column(nullable = false, length = 255)
	private String shippingAddress;

	@Column(nullable = false, length = 50)
	private String orderstatus;

	@ManyToMany
	@JoinTable(
			name = "order_product",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private Set<Product> products;
}
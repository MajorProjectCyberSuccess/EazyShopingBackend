package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Order;
import com.product.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderservice;
	
	@PostMapping("/addorder")
	public Order addOrder(@RequestBody Order order) {
		return orderservice.addOrder(order);
	}

	@GetMapping
	public List<Order> getAllOrders(){
		return orderservice.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order getOrderById (@PathVariable Long id) {
		return orderservice.getOrderById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderservice.deleteOrder(id);
	}
}


package com.eazyapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazyapp.model.Order;
import com.eazyapp.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderrepository;
	
	//Add new order
	public Order addOrder(Order order) {
		return orderrepository.save(order);
	}
	
	//get all orders
	public List<Order> getAllOrders(){
		return orderrepository.findAll();
	}
	//get order by id
	public Order getOrderById(Long orderId) {
		return orderrepository.findById(orderId).orElse(null);
	}
	//delete an order by id
	public void deleteOrder(Long orderId) {
		orderrepository.deleteById(orderId);
	}
}

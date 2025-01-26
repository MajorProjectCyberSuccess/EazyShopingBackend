package com.eazyapp.controller;

import com.eazyapp.dto.OrderDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.formatter.ResponseFormatter;
import com.eazyapp.requestwrapper.OrderRequestWrapper;
import com.eazyapp.service.OrderService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<JSONObject> createOrder(@RequestBody OrderRequestWrapper orderRequestWrapper) throws EazyShoppyException {
		System.out.println("Create order start");
		orderService.createOrder(orderRequestWrapper);
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Order created successfully");
		System.out.println("Create order end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<JSONObject> getAllOrders() {
		System.out.println("Get all orders start");
		List<OrderDTO> orders = orderService.getAllOrders();
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Orders listed successfully", orders);
		System.out.println("Get all orders end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/getOrderById")
	public ResponseEntity<JSONObject> getOrderById(@RequestHeader Long id) throws EazyShoppyException {
		System.out.println("Get order by ID start");
		OrderDTO order = orderService.getOrderById(id);
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Order retrieved successfully", order);
		System.out.println("Get order by ID end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
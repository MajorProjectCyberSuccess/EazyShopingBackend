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
	public ResponseEntity<JSONObject> createOrder(@RequestBody OrderRequestWrapper request) throws EazyShoppyException {
		orderService.createOrder(request);
		JSONObject response = ResponseFormatter.formatter("Success", 200, "Order created successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getOrderById")
	public ResponseEntity<JSONObject> getOrderById(@RequestParam long id) throws EazyShoppyException {
		OrderDTO order = orderService.getOrderById(id);
		JSONObject response = ResponseFormatter.formatter("Success", 200, "Order retrieved successfully", order);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<JSONObject> getAllOrders() throws EazyShoppyException {
		List<OrderDTO> orders = orderService.getAllOrders();
		JSONObject response = ResponseFormatter.formatter("Success", 200, "Orders listed successfully", orders);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

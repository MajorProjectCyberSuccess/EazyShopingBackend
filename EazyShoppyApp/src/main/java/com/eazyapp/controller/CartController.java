package com.eazyapp.controller;

import com.eazyapp.dto.CartDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.formatter.ResponseFormatter;
import com.eazyapp.requestwrapper.CartRequestWrapper;
import com.eazyapp.service.CartService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/create")
	public ResponseEntity<JSONObject> createCart(@RequestBody CartRequestWrapper cartRequestWrapper) throws EazyShoppyException {
		System.out.println("Create cart start");
		cartService.createCart(cartRequestWrapper);
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Cart created successfully");
		System.out.println("Create cart end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/getAllCarts")
	public ResponseEntity<JSONObject> getAllCarts() {
		System.out.println("Get all carts start");
		List<CartDTO> carts = cartService.getAllCarts();
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Carts listed successfully", carts);
		System.out.println("Get all carts end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/getCartById")
	public ResponseEntity<JSONObject> getCartById(@RequestHeader Long id) throws EazyShoppyException {
		System.out.println("Get cart by ID start");
		CartDTO cart = cartService.getCartById(id);
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Cart retrieved successfully", cart);
		System.out.println("Get cart by ID end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
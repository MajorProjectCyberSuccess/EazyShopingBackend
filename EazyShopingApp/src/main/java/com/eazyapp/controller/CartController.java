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

import com.product.model.Cart;
import com.product.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	@PostMapping("/addtocart")
	public Cart addCartItem(@RequestBody Cart cart) {
		return cartservice.addCartItem(cart);
	}

	@GetMapping
	public List<Cart> getAllCartItems(){
		return cartservice.getAllCartItems();
	}
	
	@GetMapping("/getitem/{id}")
	public Cart getCartItemById(@PathVariable Long id) {
		return cartservice.getCartItemById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCartItem(@PathVariable Long id) {
		cartservice.deleteCartItem(id);
	}
	
}

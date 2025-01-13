package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.model.Cart;
import com.product.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartrepository;
	
	public Cart addCartItem(Cart cart) {
		return cartrepository.save(cart);
	}
	
	public List<Cart> getAllCartItems(){
		return cartrepository.findAll();
	}
	
	public Cart getCartItemById(long cartId) {
		return cartrepository.findById(cartId).orElse(null);
	}
	
	public void deleteCartItem(Long cartId) {
		cartrepository.deleteById(cartId);
	}

}

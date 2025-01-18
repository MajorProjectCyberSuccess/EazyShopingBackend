package com.eazyapp.service.Implementation;

import java.util.List;

import com.eazyapp.model.Cart;
import com.eazyapp.repository.CartRepository;
import com.eazyapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CartServiceImpl implements CartService {
	
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

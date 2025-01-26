package com.eazyapp.service.Implementation;

import com.eazyapp.dto.CartDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.model.Cart;
import com.eazyapp.repository.CartRepository;
import com.eazyapp.requestwrapper.CartRequestWrapper;
import com.eazyapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public void createCart(CartRequestWrapper cartRequestWrapper) throws EazyShoppyException {
		Cart cart = new Cart();
		cart.setItemId(cartRequestWrapper.getItemId());
		cart.setQuantity(cartRequestWrapper.getQuantity());
		cart.setTotalAmount(cartRequestWrapper.getTotalAmount());

		cartRepository.save(cart);
	}

	@Override
	public CartDTO getCartById(long id) throws EazyShoppyException {
		Optional<Cart> cart = cartRepository.findById(id);
		if (cart.isPresent()) {
			CartDTO cartDTO = new CartDTO();
			cartDTO.setCartId(cart.get().getCartId());
			cartDTO.setItemId(cart.get().getItemId());
			cartDTO.setQuantity(cart.get().getQuantity());
			cartDTO.setTotalAmount(cart.get().getTotalAmount());
			return cartDTO;
		} else {
			throw new EazyShoppyException("Cart not found", 404);
		}
	}

	@Override
	public List<CartDTO> getAllCarts() throws EazyShoppyException {
		List<Cart> carts = cartRepository.findAll();
		List<CartDTO> cartDTOs = new ArrayList<>();
		for (Cart cart : carts) {
			CartDTO cartDTO = new CartDTO();
			cartDTO.setCartId(cart.getCartId());
			cartDTO.setItemId(cart.getItemId());
			cartDTO.setQuantity(cart.getQuantity());
			cartDTO.setTotalAmount(cart.getTotalAmount());
			cartDTOs.add(cartDTO);
		}
		return cartDTOs;
	}
}
package com.eazyapp.service;

import com.eazyapp.dto.CartDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.requestwrapper.CartRequestWrapper;

import java.util.List;

@Service
public interface CartService {

    void createCart(CartRequestWrapper cartRequestWrapper) throws EazyShoppyException;

    CartDTO getCartById(long id) throws EazyShoppyException;

    List<CartDTO> getAllCarts() throws EazyShoppyException;
}
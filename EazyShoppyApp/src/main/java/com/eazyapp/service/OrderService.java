package com.eazyapp.service;

import com.eazyapp.dto.OrderDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.requestwrapper.OrderRequestWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void createOrder(OrderRequestWrapper orderRequestWrapper) throws EazyShoppyException;

    OrderDTO getOrderById(long id) throws EazyShoppyException;

    List<OrderDTO> getAllOrders() throws EazyShoppyException;
}
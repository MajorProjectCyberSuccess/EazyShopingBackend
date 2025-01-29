package com.eazyapp.service.Implementation;

import com.eazyapp.dto.OrderDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.model.Order;
import com.eazyapp.repository.OrderRepository;
import com.eazyapp.requestwrapper.OrderRequestWrapper;
import com.eazyapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(OrderRequestWrapper orderRequestWrapper) throws EazyShoppyException {
        Order order = new Order();
        order.setUserId(orderRequestWrapper.getUserId());
        order.setOrderDate(orderRequestWrapper.getOrderDate());
        order.setTotalAmount(orderRequestWrapper.getTotalAmount());
        order.setShippingAddress(orderRequestWrapper.getShippingAddress());
        order.setOrderstatus(orderRequestWrapper.getOrderStatus());

        orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrderById(long id) throws EazyShoppyException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.get().getOrderId());
            orderDTO.setUserId(order.get().getUserId());
            orderDTO.setOrderDate(order.get().getOrderDate());
            orderDTO.setTotalAmount(order.get().getTotalAmount());
            orderDTO.setShippingAddress(order.get().getShippingAddress());
            orderDTO.setOrderStatus(order.get().getOrderstatus());
            return orderDTO;
        } else {
            throw new EazyShoppyException("Order not found", 404);
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() throws EazyShoppyException {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setUserId(order.getUserId());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setTotalAmount(order.getTotalAmount());
            orderDTO.setShippingAddress(order.getShippingAddress());
            orderDTO.setOrderStatus(order.getOrderstatus());
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }
}
package com.eazyapp.service.Implementation;

import com.eazyapp.dto.OrderDTO;
import com.eazyapp.dto.OrderItemDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.model.Address;
import com.eazyapp.model.Order;
import com.eazyapp.model.OrderItem;
import com.eazyapp.model.User;
import com.eazyapp.repository.OrderRepository;
import com.eazyapp.repository.ProductRepository;
import com.eazyapp.requestwrapper.OrderRequestWrapper;
import com.eazyapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createOrder(OrderRequestWrapper orderRequestWrapper) throws EazyShoppyException {
        OrderDTO orderDTO = orderRequestWrapper.getOrder();
        Order order = new Order();

        // Set order data from DTO
        order.setTotalAmount(orderDTO.getTotalAmount());

        // If needed, you can add User and Address here from DTO as well
        User user = new User();
        user.setId(orderDTO.getUserId());
        order.setUser(user);

        Address address = new Address();
        address.setId(orderDTO.getAddressId());
        order.setAddress(address);

        orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrderById(long id) throws EazyShoppyException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EazyShoppyException("Order not found", 400));

        // Convert Order model to OrderDTO
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setAddressId(order.getAddress().getId());

        // Map OrderItems to OrderItemDTO
        List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream().map(orderItem -> {
            OrderItemDTO dto = new OrderItemDTO();
            dto.setProductId(orderItem.getProduct().getProductId());
            dto.setQuantity(orderItem.getQuantity());
            dto.setPrice(orderItem.getPrice());
            return dto;
        }).collect(Collectors.toList());
        orderDTO.setOrderItems(orderItemDTOs);
        orderDTO.setTotalAmount(order.getTotalAmount());

        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders() throws EazyShoppyException {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setUserId(order.getUser().getId());
            orderDTO.setAddressId(order.getAddress().getId());

            List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream().map(orderItem -> {
                OrderItemDTO dto = new OrderItemDTO();
                dto.setProductId(orderItem.getProduct().getProductId());
                dto.setQuantity(orderItem.getQuantity());
                dto.setPrice(orderItem.getPrice());
                return dto;
            }).collect(Collectors.toList());

            orderDTO.setOrderItems(orderItemDTOs);
            orderDTO.setTotalAmount(order.getTotalAmount());
            return orderDTO;
        }).collect(Collectors.toList());
    }
}

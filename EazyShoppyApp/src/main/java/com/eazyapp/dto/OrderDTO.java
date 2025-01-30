package com.eazyapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long addressId;
    private List<OrderItemDTO> orderItems;
    private double totalAmount;
}

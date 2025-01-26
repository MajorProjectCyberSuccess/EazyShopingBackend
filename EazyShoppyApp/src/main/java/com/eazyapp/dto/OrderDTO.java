package com.eazyapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private Long userId;
    private LocalDate orderDate;
    private Double totalAmount;
    private String shippingAddress;
    private String orderStatus;
}
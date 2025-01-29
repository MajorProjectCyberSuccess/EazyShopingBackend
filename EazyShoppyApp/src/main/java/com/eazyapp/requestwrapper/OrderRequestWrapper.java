package com.eazyapp.requestwrapper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderRequestWrapper {

    private Long userId;
    private LocalDate orderDate;
    private Double totalAmount;
    private String shippingAddress;
    private String orderStatus;
}
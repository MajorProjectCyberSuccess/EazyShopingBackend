package com.eazyapp.dto;

import com.eazyapp.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private int quantity;
    private double price;

    // Getters and Setters
}

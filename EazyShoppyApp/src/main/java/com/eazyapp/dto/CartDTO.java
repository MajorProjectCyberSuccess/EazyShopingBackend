package com.eazyapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

    private long cartId;
    private long itemId;
    private int quantity;
    private double totalAmount;
}
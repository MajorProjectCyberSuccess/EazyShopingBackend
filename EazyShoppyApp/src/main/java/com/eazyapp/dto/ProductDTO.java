package com.eazyapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private long productId;
    private String name;
    private String productDetails;
    private long categoryId;
    private double price;
}
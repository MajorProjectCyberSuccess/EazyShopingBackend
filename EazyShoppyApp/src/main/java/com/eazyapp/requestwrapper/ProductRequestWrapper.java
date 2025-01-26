package com.eazyapp.requestwrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestWrapper {

    private String name;
    private String productDetails;
    private long categoryId;
    private double price;
}
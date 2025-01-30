package com.eazyapp.requestwrapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequestWrapper {

    private String name;
    private String productDetails;
    private long categoryId;
    private double price;
    private MultipartFile file;
}
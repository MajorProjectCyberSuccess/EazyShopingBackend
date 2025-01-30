package com.eazyapp.requestwrapper;

import com.eazyapp.model.Category;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequestWrapper {

    private String name;

    private String brand;

    private Long categoryId;

    private Double ratings;

    private Integer reviews;

    private double originalPrice;

    private double discount;

    private String productDescription;

}
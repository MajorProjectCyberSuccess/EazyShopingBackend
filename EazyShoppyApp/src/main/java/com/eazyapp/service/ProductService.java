package com.eazyapp.service;

import com.eazyapp.dto.ProductDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.requestwrapper.ProductRequestWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void createProduct(ProductRequestWrapper productRequestWrapper) throws EazyShoppyException;

    ProductDTO getProductById(long id) throws EazyShoppyException;

    List<ProductDTO> getAllProducts() throws EazyShoppyException;
}
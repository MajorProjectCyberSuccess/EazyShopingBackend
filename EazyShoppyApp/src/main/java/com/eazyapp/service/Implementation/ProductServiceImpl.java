package com.eazyapp.service.Implementation;

import com.eazyapp.dto.ProductDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.model.Product;
import com.eazyapp.repository.ProductRepository;
import com.eazyapp.requestwrapper.ProductRequestWrapper;
import com.eazyapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void createProduct(ProductRequestWrapper productRequestWrapper) throws EazyShoppyException {
		Optional<Product> existingProduct = productRepository.findByName(productRequestWrapper.getName());
		if (existingProduct.isPresent()) {
			throw new EazyShoppyException("Product with the same name already exists", 400);
		}

		Product product = new Product();
		product.setName(productRequestWrapper.getName());
		product.setProductDetails(productRequestWrapper.getProductDetails());
		product.setCategoryId(productRequestWrapper.getCategoryId());
		product.setPrice(productRequestWrapper.getPrice());

		productRepository.save(product);
	}

	@Override
	public ProductDTO getProductById(long id) throws EazyShoppyException {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductId(product.get().getProductId());
			productDTO.setName(product.get().getName());
			productDTO.setProductDetails(product.get().getProductDetails());
			productDTO.setCategoryId(product.get().getCategoryId());
			productDTO.setPrice(product.get().getPrice());
			return productDTO;
		} else {
			throw new EazyShoppyException("Product not found", 404);
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() throws EazyShoppyException {
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductId(product.getProductId());
			productDTO.setName(product.getName());
			productDTO.setProductDetails(product.getProductDetails());
			productDTO.setCategoryId(product.getCategoryId());
			productDTO.setPrice(product.getPrice());
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
}
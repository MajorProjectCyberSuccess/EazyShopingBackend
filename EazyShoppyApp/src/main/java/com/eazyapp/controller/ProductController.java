package com.eazyapp.controller;

import java.util.List;
import java.util.Optional;

import com.eazyapp.model.Product;
import com.eazyapp.service.Implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productservice;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productservice.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		Optional<Product> product = productservice.getProductById(id);
		return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/saveproduct")
	public Product createProduct(@RequestBody Product product) {
		return productservice.saveProduct(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product productDetails){
		Optional<Product> product = productservice.getProductById(id);
		if(product.isPresent()) {
			Product existingProduct = product.get();
		    existingProduct.setName(productDetails.getName());
		    existingProduct.setProductDetails(productDetails.getProductDetails());
		    existingProduct.setCategoryId(productDetails.getCategoryId());
		    existingProduct.setPrice(productDetails.getPrice());
		    
		    return ResponseEntity.ok(productservice.saveProduct(existingProduct));
		   
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
		productservice.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

}

package com.eazyapp.controller;

import com.eazyapp.dto.ProductDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.formatter.ResponseFormatter;
import com.eazyapp.requestwrapper.ProductRequestWrapper;
import com.eazyapp.service.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Value("${file.upload-dir}")
	private String uploadDir;


	@PostMapping(value = "/create", consumes = "multipart/form-data")
	public ResponseEntity<JSONObject> createProduct(@RequestBody ProductRequestWrapper productRequestWrapper)  throws EazyShoppyException {
		System.out.println("Create product start");
		productService.createProduct(productRequestWrapper);
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Product created successfully");
		System.out.println("Create product end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<JSONObject> getAllProducts() {
		System.out.println("Get all products start");
		List<ProductDTO> products = productService.getAllProducts();
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Products listed successfully", products);
		System.out.println("Get all products end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/getProductById")
	public ResponseEntity<JSONObject> getProductById(@RequestHeader Long id) throws EazyShoppyException {
		System.out.println("Get product by ID start");
		ProductDTO product = productService.getProductById(id);
		JSONObject data = ResponseFormatter.formatter("Success", 200, "Product retrieved successfully", product);
		System.out.println("Get product by ID end");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	@GetMapping("/images/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) {
		try {
			Path filePath = Paths.get(uploadDir).resolve(filename);
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists()) {
				return ResponseEntity.ok()
						.contentType(MediaType.IMAGE_JPEG)
						.body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (MalformedURLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
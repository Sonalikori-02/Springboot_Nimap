package com.ty.project.main.services;

import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;

import com.ty.project.main.dto.ProductRequest;
import com.ty.project.main.entities.Product;




public interface ProductService {
	Page<Product> getAllProducts(int page, int size);
//   Product createProduct(Product product);
	Product createProduct(ProductRequest productRequest);
    Product getProductById(Long id);
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
}


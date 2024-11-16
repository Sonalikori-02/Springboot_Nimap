package com.ty.project.main.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ty.project.main.dto.ProductRequest;
import com.ty.project.main.entities.Category;
import com.ty.project.main.entities.Product;
import com.ty.project.main.repositories.CategoryRepository;
import com.ty.project.main.repositories.ProductRepository;

import jakarta.transaction.Transactional;



@Service
public class ProductServiceImp1 implements ProductService {
	@Autowired
    private ProductRepository productRepository;
	

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Product> getAllProducts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepository.findAll(pageRequest);
    }

//    @Override
//    public Page<Product> getAllProducts(int page, int size) {
//        return productRepository.findAll(PageRequest.of(page, size)); //last commented this one
//    }

//    @Override
//    public Product createProduct(Product product) {
//        return productRepository.save(product);
//    }
    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest. getPrice());
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
//        productRepository.deleteById(id);
      
    }
//    @Override
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }


}
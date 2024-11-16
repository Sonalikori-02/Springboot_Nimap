package com.ty.project.main.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.project.main.dto.ProductRequest;
import com.ty.project.main.entities.Product;
import com.ty.project.main.services.ProductService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
    private ProductService productService;
	
	@GetMapping("/gett")
    public String getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<Product> productPage = productService.getAllProducts(page, size);
        System.out.println("Total products: " + productPage.getTotalElements());

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
       model.addAttribute("totalPages", productPage.getTotalPages());
       model.addAttribute("totalItems", productPage.getTotalElements());

        return "productList"; 
    }

//   @GetMapping
//   public Page<Product> getAllProducts(@RequestParam int page, @RequestParam int size) {
//      return productService.getAllProducts(page, size);
//    }


    
    
//    @PostMapping("/create")
//    public Product createProduct(@RequestBody Product product) {
//        return productService.createProduct(product);
//    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product createdProduct = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
     
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/deleting/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}

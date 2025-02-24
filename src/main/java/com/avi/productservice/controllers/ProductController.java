package com.avi.productservice.controllers;

import com.avi.productservice.dtos.CreateProductRequestDto;
import com.avi.productservice.exceptions.ProductNotFoundException;
import com.avi.productservice.models.Product;
import com.avi.productservice.services.FakeStoreProductService;
import com.avi.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getSingleProducts(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {

        return productService.createProduct(
                createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getPrice()
        );

    }

}

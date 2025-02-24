package com.avi.productservice.controllers;

import com.avi.productservice.exceptions.ProductNotFoundException;
import com.avi.productservice.models.Category;
import com.avi.productservice.models.Product;
import com.avi.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getSingleProductsPositiveCompareObjects() throws ProductNotFoundException {
        Product expectedProduct = new Product();

        when(productService.getSingleProduct(1))
                .thenReturn(expectedProduct);
        Product product = productController.getSingleProducts(1);

        assertEquals(expectedProduct, product);
    }

    @Test
    void getSingleProductsPositiveCompareObjects2() throws ProductNotFoundException {
        Product expectedProduct = new Product();
        expectedProduct.setTitle("iPhone 15 pro max");
        expectedProduct.setPrice(1500.00);

        Category category = new Category();
        category.setId(1);
        category.setTitle("Electronics");

        expectedProduct.setCategory(category);
        expectedProduct.setPrice(1500.00);


        when(productService.getSingleProduct(1))
                .thenReturn(expectedProduct);
        Product product = productController.getSingleProducts(1);

        assertEquals(expectedProduct, product);
    }

    @Test
    void createProduct() {
    }
}
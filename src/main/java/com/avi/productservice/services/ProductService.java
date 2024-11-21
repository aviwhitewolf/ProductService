package com.avi.productservice.services;

import com.avi.productservice.exceptions.ProductNotFoundException;
import com.avi.productservice.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(long id) throws ProductNotFoundException;
    Product createProduct(String title, String description, String image, String Category, Double price);
}

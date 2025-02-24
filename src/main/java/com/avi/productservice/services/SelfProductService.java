package com.avi.productservice.services;

import com.avi.productservice.exceptions.ProductNotFoundException;
import com.avi.productservice.models.Category;
import com.avi.productservice.models.Product;
import com.avi.productservice.repositories.CategoryRepository;
import com.avi.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository,
                              ProductRepository productRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " is not found in the database");
        }
        return product.get();
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, Double price) {

        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setImageUrl(image);

        Category categoryFromDB = categoryRepository.findByTitle(category);
        if(categoryFromDB == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            p.setCategory(newCategory);
        }else{
            p.setCategory(categoryFromDB);
        }


        return productRepository.save(p);
    }
}

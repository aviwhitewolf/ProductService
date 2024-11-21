package com.avi.productservice.dtos;

import com.avi.productservice.models.Category;
import com.avi.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FakeStoreProductDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);

        Category category1 = new Category();
        category1.setTitle(category);

        product.setCategory(category1);
        product.setImageUrl(image);

        return product;
    }
}

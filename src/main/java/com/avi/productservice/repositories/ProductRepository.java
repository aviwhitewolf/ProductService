package com.avi.productservice.repositories;

import com.avi.productservice.models.Category;
import com.avi.productservice.models.Product;
import com.avi.productservice.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product p);

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);


    List<Product> findByCategoryTitle(String categoryName);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByTitleStartingWithAndIdEqualsAndPriceLessThan(String startingWith, Long id, double priceLessThan);


    @Query("SELECT p.title AS title, p.id AS id FROM Product p WHERE p.category.title = :categoryName")
    List<ProductProjection> getTitlesAndIdOfAllProductsWithGivenCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "select * from products p where p.id = 1 and p.title = :productTitle", nativeQuery = true)
    List<ProductProjection> getTitlesAndIdOfAllProductsWithCategoryNameEquals(@Param("productTitle") String productTitle);



}

package com.avi.productservice.repositories;

import com.avi.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String Title);


    Optional<Category> findById(Long id);
    /*
    Select * from Category
    where title like 'title'

    convert the row to category object and return
     */

}

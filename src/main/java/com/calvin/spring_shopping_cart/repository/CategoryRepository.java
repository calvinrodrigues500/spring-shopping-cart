package com.calvin.spring_shopping_cart.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.calvin.spring_shopping_cart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}

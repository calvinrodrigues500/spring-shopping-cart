package com.calvin.spring_shopping_cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calvin.spring_shopping_cart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCetegoryName(String category);

    List<Product> findByBrand(String brand);

	List<Product> findByCetegoryNameAndBrand(String category, String brand);

	List<Product> findByName(String name);

	List<Product> findByBrandAndName(String brand, String name);

	Long countByBrandAndName(String brand, String name);

}

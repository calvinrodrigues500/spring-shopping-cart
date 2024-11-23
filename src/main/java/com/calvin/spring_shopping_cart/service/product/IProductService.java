package com.calvin.spring_shopping_cart.service.product;

import java.util.List;

import com.calvin.spring_shopping_cart.model.Product;
import com.calvin.spring_shopping_cart.request.AddProductRequest;
import com.calvin.spring_shopping_cart.request.ProductUpdateRequest;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product geProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}

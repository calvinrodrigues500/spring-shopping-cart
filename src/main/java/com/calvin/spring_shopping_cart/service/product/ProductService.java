package com.calvin.spring_shopping_cart.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.calvin.spring_shopping_cart.exceptions.ProdcutNotFoundException;
import com.calvin.spring_shopping_cart.model.Category;
import com.calvin.spring_shopping_cart.model.Product;
import com.calvin.spring_shopping_cart.repository.CategoryRepository;
import com.calvin.spring_shopping_cart.repository.ProductRepository;
import com.calvin.spring_shopping_cart.request.AddProductRequest;
import com.calvin.spring_shopping_cart.request.ProductUpdateRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                request.getCategory());
    }

    @Override
    public Product geProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProdcutNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        () -> {
                            throw new ProdcutNotFoundException("Product not found");
                        });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .orElseThrow(() -> new ProdcutNotFoundException("Product not found"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCetegoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCetegoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}

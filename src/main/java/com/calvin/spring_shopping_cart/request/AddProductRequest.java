package com.calvin.spring_shopping_cart.request;
    
import java.math.BigDecimal;

import com.calvin.spring_shopping_cart.model.Category;

import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;

}

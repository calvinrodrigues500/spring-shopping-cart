package com.calvin.spring_shopping_cart.exceptions;

public class ProdcutNotFoundException extends RuntimeException {
    public ProdcutNotFoundException(String message) {
        super(message);
    }
}

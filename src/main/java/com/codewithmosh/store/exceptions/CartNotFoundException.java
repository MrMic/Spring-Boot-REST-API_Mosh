package com.codewithmosh.store.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException() {
        super("The cart was not found.");
    }
}

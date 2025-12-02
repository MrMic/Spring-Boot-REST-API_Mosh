package com.codewithmosh.store.exceptions;

import com.codewithmosh.store.dtos.OrderDto;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found");
    }
}

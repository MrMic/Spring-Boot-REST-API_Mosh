package com.codewithmosh.store.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartItemDto {
  private cartProductDto product;
  private int quantity;
  private BigDecimal totalPrice;
}

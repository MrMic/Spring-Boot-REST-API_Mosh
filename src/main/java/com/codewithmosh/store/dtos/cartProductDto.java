package com.codewithmosh.store.dtos;

import java.math.BigDecimal;

import lombok.Data;

/**
 * cartProductDto
 */

@Data
public class cartProductDto {
  private Long id;
  private String name;
  private BigDecimal price;

}

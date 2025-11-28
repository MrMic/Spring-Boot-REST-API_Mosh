package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.services.OrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  @GetMapping
  public List<OrderDto> getAllOrders() {
    return orderService.getAllOrders();
  }
}

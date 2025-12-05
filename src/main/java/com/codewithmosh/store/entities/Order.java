package com.codewithmosh.store.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private User customer;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  // * INFO: The database will automatically set this value when the record is
  // created
  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private Set<OrderItem> items = new LinkedHashSet<>();

  // ______________________________________________________________________
  public static Order fromCart(Cart cart, User customer) {

    var order = new Order();
    order.setCustomer(customer);
    order.setStatus(OrderStatus.PENDING);
    order.setTotalPrice(cart.getTotalPrice());

    cart.getItems()
        .forEach(
            item -> {
              var orderItem = new OrderItem(order, item.getProduct(), item.getQuantity());
              order.items.add(orderItem);
            });

    return order;
  }

  public boolean isPLaced(User customer) {
    return this.customer.equals(customer);
  }
}

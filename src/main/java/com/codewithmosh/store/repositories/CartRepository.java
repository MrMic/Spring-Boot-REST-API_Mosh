package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Cart;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, UUID> {}

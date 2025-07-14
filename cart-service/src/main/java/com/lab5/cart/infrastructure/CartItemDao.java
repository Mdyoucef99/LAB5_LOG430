package com.lab5.cart.infrastructure;

import com.lab5.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);
} 
package com.lab5.cart.infrastructure;

import com.lab5.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomerId(Long customerId);
} 
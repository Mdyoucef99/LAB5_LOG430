package com.lab5.cart.api;

import com.lab5.cart.infrastructure.CartDao;
import com.lab5.cart.infrastructure.CartItemDao;
import com.lab5.cart.domain.Cart;
import com.lab5.cart.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartDao cartDao;
    private final CartItemDao cartItemDao;

    @Autowired
    public CartController(CartDao cartDao, CartItemDao cartItemDao) {
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        try {
            return ResponseEntity.ok(cartDao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        try {
            cartDao.create(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable int id) {
        try {
            Cart cart = cartDao.findById(id);
            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(cart);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable int cartId, @RequestBody CartItem item) {
        try {
            CartItem newItem = new CartItem(cartId, item.getProductId(), item.getQuantity());
            cartItemDao.create(newItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int cartId) {
        try {
            return ResponseEntity.ok(cartItemDao.findByCartId(cartId));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
} 
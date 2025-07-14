package com.lab5.cart.api;

import com.lab5.cart.infrastructure.CartDao;
import com.lab5.cart.infrastructure.CartItemDao;
import com.lab5.cart.domain.Cart;
import com.lab5.cart.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;

    @GetMapping
    public List<Cart> getAllCarts() throws SQLException {
        return cartDao.findAll();
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) throws SQLException {
        cartDao.create(cart);
        return cart;
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable int id) throws SQLException {
        return cartDao.findById(id);
    }

    @PostMapping("/{cartId}/items")
    public CartItem addItemToCart(@PathVariable int cartId, @RequestBody CartItem item) throws SQLException {
        // On force le cartId reçu dans l'URL
        CartItem newItem = new CartItem(cartId, item.getProductId(), item.getQuantity());
        cartItemDao.create(newItem);
        return newItem;
    }

    @GetMapping("/{cartId}/items")
    public List<CartItem> getCartItems(@PathVariable int cartId) throws SQLException {
        return cartItemDao.findByCartId(cartId);
    }
} 
package com.lab5.cart.api;

import com.lab5.cart.infrastructure.CartDao;
import com.lab5.cart.infrastructure.CartItemDao;
import com.lab5.cart.domain.Cart;
import com.lab5.cart.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartDao.findAll();
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartDao.save(cart);
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartDao.findById(id).orElse(null);
    }

    @PostMapping("/{cartId}/items")
    public CartItem addItemToCart(@PathVariable Long cartId, @RequestBody CartItem item) {
        item.setCartId(cartId);
        return cartItemDao.save(item);
    }

    @GetMapping("/{cartId}/items")
    public List<CartItem> getCartItems(@PathVariable Long cartId) {
        return cartItemDao.findAll().stream().filter(i -> i.getCartId().equals(cartId)).toList();
    }
} 
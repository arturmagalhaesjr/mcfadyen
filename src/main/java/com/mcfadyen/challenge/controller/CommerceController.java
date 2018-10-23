package com.mcfadyen.challenge.controller;

import com.mcfadyen.challenge.model.CommerceItem;
import com.mcfadyen.challenge.model.Product;
import com.mcfadyen.challenge.model.ShoppingCart;
import com.mcfadyen.challenge.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@SessionAttributes("shoppingCart")
public class CommerceController {

    private final ProductRepository productRepository;

    private ShoppingCart handleCart (ShoppingCart cart) {
        return (cart != null) ? cart : new ShoppingCart();
    }

    CommerceController (ProductRepository repository) {
        this.productRepository = repository;
    }

    @GetMapping("/products")
    List<Product> getProducts (HttpSession session) {
        return this.productRepository.findAll();
    }

    @GetMapping("/shoppingcart")
    ShoppingCart getShoppingCart (@ModelAttribute("shoppingCart") ShoppingCart cart, HttpSession session) {
        return this.handleCart(cart);
    }

    @PostMapping("/shoppingcart/items")
    CommerceItem addItem (@ModelAttribute("shoppingCart") ShoppingCart cart, @RequestParam Long product_id, @RequestParam int quantity, HttpSession session) throws Exception {
        CommerceItem commerceItem = new CommerceItem();
        commerceItem.setProduct(productRepository.findById(product_id)
                .orElseThrow(() -> new Exception("Cannot find the product")));
        commerceItem.setQuantity(quantity);

        cart = handleCart(cart);
        return cart.addCommerceItem(commerceItem);
    }

    @DeleteMapping("/shoppingcart/items/{id}")
    CommerceItem deleteItem (@ModelAttribute("shoppingCart") ShoppingCart cart, @PathVariable int id, HttpSession session) throws Exception {
        cart = handleCart(cart);
        return cart.removeCommerceItemById(id);
    }

    @ModelAttribute("shoppingCart")
    public ShoppingCart setUpCart () {
        return new ShoppingCart();
    }

}

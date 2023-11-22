//package com.samiul.demo.controller;
//
//import com.samiul.demo.model.Cart;
//import com.samiul.demo.model.User;
//import com.samiul.demo.model.products;
//import com.samiul.demo.repository.CartRepository;
//import com.samiul.demo.repository.UserRepository;
//import com.samiul.demo.repository.productRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin("http://localhost:3000")
//public class CartController {
//
//    private final CartRepository cartRepository;
//    private final UserRepository userRepository;
//    private final productRepository productRepository;
//
//    public CartController(CartRepository cartRepository,UserRepository userRepository, com.samiul.demo.repository.productRepository productRepository) {
//        this.cartRepository = cartRepository;
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//    }
//
//
//
//    @PostMapping("/add-to-cart/{userId}/{productId}")
//    public ResponseEntity<String> addToCart(@PathVariable Long userId, @PathVariable Long productId) {
//        System.out.print("Samir1");
//        User user = userRepository.findById(userId).orElse(null);
//        products product = productRepository.findById(productId).orElse(null);
//
//        System.out.print("Samir");
//        System.out.print(user);
//        System.out.print(product);
//
//        if (user == null || product == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
//        } else {
//            Cart cart = new Cart();
//            cart.setUser(user);
//            cart.setProduct(product);
//
//            cartRepository.save(cart);
//
//            return ResponseEntity.ok("Product added to cart successfully");
//
//        }
//
//
//    }
//}

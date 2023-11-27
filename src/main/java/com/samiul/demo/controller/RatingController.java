package com.samiul.demo.controller;


import com.samiul.demo.domain.RatingRequest;
import com.samiul.demo.model.ProductRating;
import com.samiul.demo.model.User;
import com.samiul.demo.model.products;
import com.samiul.demo.repository.RatingRepository;
import com.samiul.demo.repository.UserRepository;
import com.samiul.demo.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/rating")
@CrossOrigin("http://localhost:3000")
public class RatingController {
    @Autowired
    private productRepository productRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ProductRating> newProduct(@RequestBody @Validated RatingRequest request){
        User user = this.userRepository.findByUsername(request.getUserName())
                .orElseThrow();
        products products = this.productRepository.findById(request.getProductId())
                .orElseThrow();
        ProductRating productRating = new ProductRating();
        productRating.setUser(user);
        productRating.setProducts(products);
        productRating.setRating(request.getRating());
        productRating.setReview(request.getReview());
        return ResponseEntity.ok(this.ratingRepository.save(productRating));
    }

    @DeleteMapping("{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId){
        this.ratingRepository.deleteById(ratingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userEmail}")
    @Transactional
    public ResponseEntity<List<ProductRating>> getByUser(@PathVariable String userEmail){
        return ResponseEntity.ok(this.ratingRepository.findByUserName(userEmail));
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<List<ProductRating>> getAll(@PathVariable Long id) {
        return ResponseEntity.ok(this.ratingRepository.findByProductId(id));
    }
}
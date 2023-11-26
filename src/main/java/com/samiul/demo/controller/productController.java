package com.samiul.demo.controller;


import com.samiul.demo.model.products;
import com.samiul.demo.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class productController {
    @Autowired
    private productRepository productRepository;

    //    @PostMapping("/products")
//     public products newProduct(@RequestBody products newProduct){
//        return productRepository.save(newProduct);
//    }
    @PostMapping("/add")
    public ResponseEntity<products> newProduct(@RequestBody products newProduct){
        return new ResponseEntity<products>(productRepository.save(newProduct), HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public List<products> getAllproducts(){
        return  productRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public Optional<products> getProductById(@PathVariable long id){

        return productRepository.findById(id);
    }
    @PutMapping ("/update/{id}")
    public products updateProduct(@PathVariable long id,@RequestBody products prod){
        prod.setId(id);
        return productRepository.save(prod);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id){
        productRepository.deleteById(id);
    }


    @PutMapping("/{productId}/decrement")
    public products decrementProductQuantity(@PathVariable Long productId, @RequestParam int quantity) {
        products product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        int currentQuantity = product.getQuantity();
        int updatedQuantity = currentQuantity - quantity;


        product.setQuantity(updatedQuantity);
        return productRepository.save(product);
    }

    @PutMapping ("/updateView/{id}")
    public products updateProductView(@PathVariable long id){
        products product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        int currentViews = product.getView();
        product.setView(currentViews + 1);
        return productRepository.save(product);
    }

}
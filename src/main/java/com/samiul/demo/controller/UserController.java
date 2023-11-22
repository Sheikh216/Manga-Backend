package com.samiul.demo.controller;


import com.samiul.demo.exception.UserNotFoundException;
import com.samiul.demo.model.User;
import com.samiul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// for OTP

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    } //newuser = {username: samir,username, email, password:123}

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();

    }


//    @PutMapping("/forget")
////    public User getById(@RequestParam Long id,@RequestBody User newUser){
////        newUser.setId(id);
////        return userRepository.save(newUser);
////    }

//change password , before --> ("/forget")
    @PutMapping("/changePass")
    public ResponseEntity<User> updateUser(@RequestBody User newUser) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(newUser.getUsername(), newUser.getPassword());
        System.out.println("Received request with username: " + newUser.getUsername());
        System.out.println("Received request with MobileNo: " + newUser.getMobileNo());
        System.out.println("Received request with newPassword: " + newUser.getNewPassword());
        System.out.println("Received request with userOptional: " + userOptional);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newUser.getNewPassword());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/forget")
    public ResponseEntity<User> changePass(@RequestBody User newUser) {
        Optional<User> userOptional = userRepository.findByUsernameAndMobileNo(newUser.getUsername(), newUser.getMobileNo());
        System.out.println("Received request with username: " + newUser.getUsername());
        System.out.println("Received request with MobileNo: " + newUser.getMobileNo());
        System.out.println("Received request with newPassword: " + newUser.getNewPassword());
        System.out.println("Received request with userOptional: " + userOptional);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newUser.getNewPassword());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }






//    @GetMapping("/user/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }







//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
//        Optional<User> userOptional = userRepository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
//
//        if (userOptional.isPresent()) {
//            // User is authenticated, you can generate a JWT token or set a session here
//            return ResponseEntity.ok("Login successful!");
//
//        } else {
//            // Invalid credentials
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//
//        }
//    }
// loginuser-->username,name,password
// response Entity helps us to send the status.


    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        System.out.println("Received request with username: " + loginUser.getUsername());
        System.out.println("Received request with MobileNo: " + loginUser.getMobileNo());
        System.out.println("Received request with newPassword: " + loginUser.getPassword());
        System.out.println("Received request with userOptional: " + userOptional);
        if (userOptional.isPresent()) {
            System.out.println("Received request with username: " + loginUser.getUsername());
            System.out.println("Received request with MobileNo: " + loginUser.getMobileNo());
            System.out.println("Received request with newPassword: " + loginUser.getPassword());
            System.out.println("Received request with userOptional: " + userOptional);


            // User is authenticated, you can generate a JWT token or set a session here

            User authenticatedUser = userOptional.get();
            authenticatedUser.setLogin(true); // Set login property to true
            userRepository.save(authenticatedUser); // Save the updated user object back to the database
            return ResponseEntity.ok(authenticatedUser); // Return the user object
        } else {
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }







//    @PostMapping("/login")
//    public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
//        Optional<User> userOptional = userRepository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setLogin(true);
//
//
//
//            // Save the updated user object back to the database
//            userRepository.save(user);
//
//            return ResponseEntity.ok(user); // Return the user object without the password
//        } else {
//            // Invalid credentials
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//    }


//        @PostMapping("/login")
//    public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
//        Optional<User> student = userRepository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
//
//        if (student.isPresent()) {
//
//            userRepository.save(loginUser);
//
//            return ResponseEntity.ok(loginUser);
//
//        } else {
//            // Invalid credentials
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//    }
//


    //
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null); // Returns null if user not found
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }


    @DeleteMapping("/user/{id}")
    String deleteuser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with " + id + " has been deleted successfully.";

    }


    //SET ADMIN

    @PutMapping("/newAdmin/{id}")
    public ResponseEntity<User> updateAdmin(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        System.out.print(userOptional);
        if (userOptional.isPresent()) {
            User newAdmin = userOptional.get();
            newAdmin.setAdmin(true);
            userRepository.save(newAdmin);
            return ResponseEntity.ok(newAdmin);

        } else {
            return ResponseEntity.notFound().build();

        }
    }

    //PRO

    @PutMapping("/PRO")
    public ResponseEntity<User> updatePRO(@RequestBody User newUser) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(newUser.getUsername(), newUser.getPassword());
        System.out.print(userOptional);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPremier(true);
            userRepository.save(user);
            return ResponseEntity.ok(user);

        } else {
            return ResponseEntity.notFound().build();

        }
    }


//    @PostMapping("/user/{userId}/cart/add/{productId}")
//    public ResponseEntity<String> addToCart(@PathVariable Long userId, @PathVariable Long productId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            List<Long> cart = user.getCart();
//
//            // Check if the product ID already exists in the user's cart
//            if (cart.contains(productId)) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product already exists in the user's cart");
//            }
//
//            // Add the new product ID to the cart
//            cart.add(productId);
//            user.setCart(cart);
//
//            userRepository.save(user);
//
//            return ResponseEntity.ok("Product added to cart successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }
}


    // Forget Password using OTP



package com.samiul.demo.controller;


import com.samiul.demo.exception.UserNotFoundException;
import com.samiul.demo.model.User;
import com.samiul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();

    }


//    @GetMapping("/user/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }




    //chatgpt


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());

        if (userOptional.isPresent()) {
            // User is authenticated, you can generate a JWT token or set a session here
            return ResponseEntity.ok("Login successful!");
        } else {
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

//
//    @GetMapping("/user/{id}")
//    public User getUserById(@PathVariable Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        return userOptional.orElse(null); // Returns null if user not found
//    }


//    @PutMapping("/users/{id}")
//    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setUsername(newUser.getUsername());
//                    user.setName(newUser.getName());
//                    user.setEmail(newUser.getEmail());
//                    // If you have other fields to update, add them here
//                    return userRepository.save(user);
//                })
//                .orElseGet(() -> {
//                    // Handle the case where the user with the given ID is not found
//                    newUser.setId(id);
//                    return userRepository.save(newUser);
//                });
//    }





}
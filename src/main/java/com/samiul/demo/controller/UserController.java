package com.samiul.demo.controller;


import com.samiul.demo.exception.UserNotFoundException;
import com.samiul.demo.model.User;
import com.samiul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (userOptional.isPresent()) {
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

}
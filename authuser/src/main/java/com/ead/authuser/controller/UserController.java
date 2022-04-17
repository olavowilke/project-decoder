package com.ead.authuser.controller;

import com.ead.authuser.model.User;
import com.ead.authuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "userId") UUID userId) {
        Optional<User> userOptional = userService.findById(userId);
        return userOptional.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")
                : ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        } else {
            userService.deleteUser(userOptional.get());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

}

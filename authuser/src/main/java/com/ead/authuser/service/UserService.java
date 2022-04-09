package com.ead.authuser.service;

import com.ead.authuser.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(UUID userId);

    ResponseEntity<Object> deleteUser(User userOptional);

}

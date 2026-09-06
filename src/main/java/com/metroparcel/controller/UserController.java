package com.metroparcel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.metroparcel.model.User;
import com.metroparcel.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user) {

        try {

            User savedUser =
                    service.register(user);

            Map<String, Object> response =
                    new HashMap<>();

            response.put(
                    "message",
                    "Registration successful"
            );

            response.put(
                    "user",
                    savedUser
            );

            return ResponseEntity.ok(response);

        }
        catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    e.getMessage()
                            )
                    );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody User loginRequest) {

        try {

            User user =
                    service.login(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    );

            Map<String, Object> response =
                    new HashMap<>();

            response.put(
                    "message",
                    "Login successful"
            );

            response.put(
                    "user",
                    user
            );

            return ResponseEntity.ok(response);

        }
        catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    e.getMessage()
                            )
                    );
        }
    }
}
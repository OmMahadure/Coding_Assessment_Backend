package com.main.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.main.dto.LoginRequest;
import com.main.dto.LoginResponse;
import com.main.dto.RegisterRequest;
import com.main.dto.RegisterResponse;
import com.main.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins ="http://localhost:5173")
public class AuthController {

	private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(
            Authentication authentication) {

        return ResponseEntity.ok(
            Map.of(
                "email", authentication.getName(),
                "message", "JWT authentication successful"
            )
        );
    }
		
}

package com.darshan.AnnotationPortal.controller;

import com.darshan.AnnotationPortal.dto.AuthResponse;
import com.darshan.AnnotationPortal.dto.LoginRequest;
import com.darshan.AnnotationPortal.dto.RegisterRequest;
import com.darshan.AnnotationPortal.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}
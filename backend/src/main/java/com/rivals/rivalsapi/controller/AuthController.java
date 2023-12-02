package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.dto.auth.AuthRequestDto;
import com.rivals.rivalsapi.dto.auth.RegisterDto;
import com.rivals.rivalsapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterDto registerDto
    ) {
        try {
            return ResponseEntity.ok(authService.register(registerDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthRequestDto authDto
    ) {
        try {
            return ResponseEntity.ok(authService.authenticate(authDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

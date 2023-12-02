package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.dto.auth.AuthRequestDto;
import com.rivals.rivalsapi.dto.auth.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterDto registerDto
    ) {
        try {
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/login")
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthRequestDto authDto
    ) {
        try {
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

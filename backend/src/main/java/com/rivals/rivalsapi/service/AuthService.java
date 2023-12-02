package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.auth.AuthRequestDto;
import com.rivals.rivalsapi.dto.auth.AuthResponseDto;
import com.rivals.rivalsapi.dto.auth.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthResponseDto register(RegisterDto registerDto) {
        return null;
    }

    public AuthResponseDto authenticate(AuthRequestDto authDto) {
        return null;
    }
}

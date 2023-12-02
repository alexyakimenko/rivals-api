package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.auth.AuthRequestDto;
import com.rivals.rivalsapi.dto.auth.AuthResponseDto;
import com.rivals.rivalsapi.dto.auth.RegisterDto;
import com.rivals.rivalsapi.model.Role;
import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDto register(RegisterDto registerDto) {
        User user = User.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponseDto authenticate(AuthRequestDto authDto) {
        return null;
    }
}

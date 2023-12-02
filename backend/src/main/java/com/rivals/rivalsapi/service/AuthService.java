package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.auth.AuthRequestDto;
import com.rivals.rivalsapi.dto.auth.AuthResponseDto;
import com.rivals.rivalsapi.dto.auth.RegisterDto;
import com.rivals.rivalsapi.model.Role;
import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDto.getUsername(),
                        authDto.getPassword()
                )
        );
        User user = userRepository.findByUsername(authDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}

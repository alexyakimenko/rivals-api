package com.rivals.rivalsapi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {
    private static final String SECRET_KEY = "xXopuniMHtHJ6px5xmLcD9ebnf4dmZhW";
    public String extractUsername(String token) {
        return null;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        // TODO: implement getSignInKey method
        return null;
    }
}

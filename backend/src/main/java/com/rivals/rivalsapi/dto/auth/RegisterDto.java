package com.rivals.rivalsapi.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}

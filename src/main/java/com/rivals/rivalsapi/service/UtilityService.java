package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilityService {
    private final UserRepository userRepository;

    public User getContextUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Pair<User, User> getContextUsers(String username) {
        Authentication authorization = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authorization.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User target = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new Pair<>(user, target);
    }
}

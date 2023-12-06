package com.rivals.rivalsapi.repository;

import com.rivals.rivalsapi.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTests {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findByUsername_shouldFindUser() {
        User user = User.builder()
                .username("username")
                .password("testpassword")
                .firstName("Alexandr")
                .build();
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByUsername("username");
        Assertions.assertTrue(savedUser.isPresent());
    }

    @Test
    void findByUsername_shouldNotFindUser() {
        Optional<User> savedUser = userRepository.findByUsername("username");
        Assertions.assertTrue(savedUser.isEmpty());
    }
}

package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", required = false) int pageSize
    ) {
        try {
            return ResponseEntity.ok(userService.getAllUsers(pageNumber, pageSize));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/following")
    public ResponseEntity<Object> getFollowing() {
        try {
            return ResponseEntity.ok(userService.getFollowing());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/followers")
    public ResponseEntity<Object> getFollowers() {
        try {
            return ResponseEntity.ok(userService.getFollowers());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/following")
    public ResponseEntity<Object> followUser(
            @RequestParam String username
    ) {
        try {
            return ResponseEntity.ok(userService.followUser(username));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(path = "/following")
    public ResponseEntity<Object> unfollowUser(
            @RequestParam String username
    ) {
        try {
            return ResponseEntity.ok(userService.unfollowUser(username));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

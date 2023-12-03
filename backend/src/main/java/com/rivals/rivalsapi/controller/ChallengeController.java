package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.dto.challenge.AddChallengeDto;
import com.rivals.rivalsapi.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/challenges")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<Object> getAllChallenges() {
        try {
            return ResponseEntity.ok(challengeService.getAllChallenges());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{challenge_id}")
    public ResponseEntity<Object> getChallengeById(
            @PathVariable Long challenge_id
    ) {
        try {
            return ResponseEntity.ok(challengeService.getChallengeById(challenge_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addChallenge(
            @RequestBody AddChallengeDto challengeDto
    ) {
        try {
            return ResponseEntity.ok(challengeService.addChallenge(challengeDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

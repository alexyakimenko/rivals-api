package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.dto.challenge.AddChallengeDto;
import com.rivals.rivalsapi.service.ChallengeService;
import com.rivals.rivalsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/challenges")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllChallenges(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", required = false) int pageSize
    ) {
        try {
            return ResponseEntity.ok(challengeService.getAllChallenges(pageNumber, pageSize));
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

    @GetMapping(path = "/starred")
    public ResponseEntity<Object> getStarred(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", required = false) int pageSize
    ) {
        try {
            return ResponseEntity.ok(userService.getStarredChallenges(pageNumber, pageSize));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/starred/{challenge_id}")
    public ResponseEntity<Object> starChallenge(
            @PathVariable Long challenge_id
    ) {
        try {
            return ResponseEntity.ok(userService.starChallenge(challenge_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/starred/{challenge_id}")
    public ResponseEntity<Object> unstarChallenge(
            @PathVariable Long challenge_id
    ) {
        try {
            return ResponseEntity.ok(userService.unstarChallenge(challenge_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addChallenge(
            @RequestBody @NonNull AddChallengeDto challengeDto
    ) {
        try {
            return ResponseEntity.ok(challengeService.addChallenge(challengeDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{challenge_id}")
    public ResponseEntity<Object> updateChallenge(
            @PathVariable Long challenge_id,
            @RequestBody AddChallengeDto challengeDto
    ) {
        try {
            return ResponseEntity.ok(challengeService.updateChallenge(challenge_id, challengeDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{challenge_id}")
    public ResponseEntity<Object> deleteChallenge(
            @PathVariable Long challenge_id
    ) {
        try {
            return ResponseEntity.ok(challengeService.deleteChallenge(challenge_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.dto.competition.AddCompetitionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/competition")
public class CompetitionController {
    @PostMapping(path = "/new")
    public ResponseEntity<Object> newCompetition(
        @RequestBody AddCompetitionDto competitionDto
    ) {
        try {
            return ResponseEntity.ok(""); // TODO: implement competition service
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/{competition_id}")
    public ResponseEntity<Object> getCompetition(
            @RequestBody Long competition_id
    ) {
        try {
            return ResponseEntity.ok(""); // TODO: implement competition service
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{competition_id}")
    public ResponseEntity<Object> acceptCompetition(
            @RequestBody Long competition_id
    ) {
        try {
            return ResponseEntity.ok(""); // TODO: implement competition service
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{competition_id}")
    public ResponseEntity<Object> deleteCompetition(
            @RequestBody Long competition_id
    ) {
        try {
            return ResponseEntity.ok(""); // TODO: implement competition service
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
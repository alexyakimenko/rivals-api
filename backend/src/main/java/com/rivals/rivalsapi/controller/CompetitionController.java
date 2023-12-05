package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.dto.competition.AddCompetitionDto;
import com.rivals.rivalsapi.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;

    @PostMapping(path = "/new")
    public ResponseEntity<Object> newCompetition(
        @RequestBody AddCompetitionDto competitionDto
    ) {
        try {
            return ResponseEntity.ok(competitionService.newCompetition(competitionDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{competition_id}")
    public ResponseEntity<Object> getCompetition(
            @PathVariable Long competition_id
    ) {
        try {
            System.out.println("Why not");
            return ResponseEntity.ok(competitionService.getCompetition(competition_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{competition_id}")
    public ResponseEntity<Object> acceptCompetition(
            @PathVariable Long competition_id
    ) {
        try {
            return ResponseEntity.ok(competitionService.acceptCompetition(competition_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{competition_id}")
    public ResponseEntity<Object> deleteCompetition(
            @PathVariable Long competition_id
    ) {
        try {
            return ResponseEntity.ok(competitionService.deleteCompetition(competition_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
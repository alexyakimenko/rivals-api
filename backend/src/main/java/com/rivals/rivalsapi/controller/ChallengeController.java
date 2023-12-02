package com.rivals.rivalsapi.controller;

import com.rivals.rivalsapi.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
}

package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.challenge.AddChallengeDto;
import com.rivals.rivalsapi.dto.challenge.ChallengeDto;
import com.rivals.rivalsapi.model.Challenge;
import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.ChallengeRepository;
import com.rivals.rivalsapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public ChallengeDto addChallenge(AddChallengeDto challengeDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Challenge challenge = AddChallengeDto.toChallenge(challengeDto);
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        challenge.setCreator(user);
        challengeRepository.save(challenge);
        return ChallengeDto.fromChallenge(challenge);
    }

    public List<ChallengeDto> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(ChallengeDto::fromChallenge)
                .collect(Collectors.toList());
    }

    public Object getChallengeById(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
    }
}

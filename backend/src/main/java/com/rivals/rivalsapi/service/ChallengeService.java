package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.challenge.AddChallengeDto;
import com.rivals.rivalsapi.dto.challenge.ChallengeDto;
import com.rivals.rivalsapi.model.Challenge;
import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.ChallengeRepository;
import com.rivals.rivalsapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public ChallengeDto addChallenge(AddChallengeDto challengeDto) {
        Challenge challenge = AddChallengeDto.toChallenge(challengeDto);
        User user = getContextUser();
        challenge.setCreator(user);
        return ChallengeDto.fromChallenge(challengeRepository.save(challenge));
    }

    public Page<ChallengeDto> getAllChallenges(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return challengeRepository.findAll(pageable)
                .map(ChallengeDto::fromChallenge);
    }

    public ChallengeDto getChallengeById(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        return ChallengeDto.fromChallenge(challenge);
    }

    public ChallengeDto deleteChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = getContextUser();
        if(!Objects.equals(user.getId(), challenge.getCreator().getId())) throw new PermissionDeniedDataAccessException("You have no rights to do this action", new Throwable());
        challengeRepository.delete(challenge);
        return ChallengeDto.fromChallenge(challenge);
    }

    public ChallengeDto updateChallenge(Long challengeId, AddChallengeDto challengeDto) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = getContextUser();
        if(!Objects.equals(user.getId(), challenge.getCreator().getId())) throw new PermissionDeniedDataAccessException("You have no rights to do this action", new Throwable());

        if(
                challengeDto.getTitle() != null &&
                !challengeDto.getTitle().isBlank()
        ) challenge.setTitle(challengeDto.getTitle());
        if(
                challengeDto.getDescription() != null &&
                !challengeDto.getDescription().isBlank()
        ) challenge.setDescription(challengeDto.getDescription());


        return ChallengeDto.fromChallenge(challengeRepository.save(challenge));
    }

    private User getContextUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

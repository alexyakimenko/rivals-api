package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.challenge.AddChallengeDto;
import com.rivals.rivalsapi.dto.challenge.ChallengeDto;
import com.rivals.rivalsapi.model.Challenge;
import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.ChallengeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UtilityService utilityService;
    private final static Logger logger = LoggerFactory.getLogger(ChallengeService.class);

    public ChallengeDto addChallenge(AddChallengeDto challengeDto) {
        Challenge challenge = AddChallengeDto.toChallenge(challengeDto);
        User user = utilityService.getContextUser();
        challenge.setCreator(user);
        ChallengeDto challengeResponse = ChallengeDto.fromChallenge(challengeRepository.save(challenge));
        logger.info("Challenge with id: {}, has been added", challengeResponse.getId());
        return challengeResponse;
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

    public Challenge getActualChallengeById(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
    }

    public ChallengeDto deleteChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = utilityService.getContextUser();
        if(!Objects.equals(user.getId(), challenge.getCreator().getId())) throw new PermissionDeniedDataAccessException("You have no rights to do this action", new Throwable());
        challengeRepository.delete(challenge);
        logger.info("Challenge with id: {}, has been deleted", challenge.getId());
        return ChallengeDto.fromChallenge(challenge);
    }

    public ChallengeDto updateChallenge(Long challengeId, AddChallengeDto challengeDto) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = utilityService.getContextUser();
        if(!Objects.equals(user.getId(), challenge.getCreator().getId())) throw new PermissionDeniedDataAccessException("You have no rights to do this action", new Throwable());

        if(
                challengeDto.getTitle() != null &&
                !challengeDto.getTitle().isBlank()
        ) challenge.setTitle(challengeDto.getTitle());
        if(
                challengeDto.getDescription() != null &&
                !challengeDto.getDescription().isBlank()
        ) challenge.setDescription(challengeDto.getDescription());

        logger.info("Challenge with id: {}, has been updated", challenge.getId());
        return ChallengeDto.fromChallenge(challengeRepository.save(challenge));
    }
}

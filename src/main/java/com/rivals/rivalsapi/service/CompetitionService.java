package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.competition.AddCompetitionDto;
import com.rivals.rivalsapi.dto.competition.CompetitionDto;
import com.rivals.rivalsapi.model.Challenge;
import com.rivals.rivalsapi.model.Competition;
import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.ChallengeRepository;
import com.rivals.rivalsapi.repository.CompetitionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ChallengeService challengeService;
    private final UtilityService utilityService;
    private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);

    public CompetitionDto newCompetition(AddCompetitionDto addCompetitionDto) {
        Pair<User, User> contextUsers = utilityService.getContextUsers(addCompetitionDto.getTargetUsername());
        Challenge challenge = challengeService.getActualChallengeById(addCompetitionDto.getChallengeId());
        Competition competition = Competition.builder()
                .challenger(contextUsers.a)
                .competitor(contextUsers.b)
                .challenge(challenge)
                .accepted(false)
                .build();
        CompetitionDto competitionDto = CompetitionDto.fromCompetition(
                competitionRepository.save(competition)
        );
        logger.info("Competition with id: {} between {} and {} has been created",
                competition.getId(),
                competition.getChallenger().getUsername(),
                competition.getCompetitor().getUsername()
        );

        return competitionDto;
    }

    public CompetitionDto getCompetition(Long competitionId) {
        return CompetitionDto.fromCompetition(getActualCompetition(competitionId));
    }

    private Competition getActualCompetition(Long competitionId) {
        return competitionRepository.findById(competitionId)
                .orElseThrow(() -> new EntityNotFoundException("Competition not found"));
    }

    public CompetitionDto acceptCompetition(Long competitionId) {
        Competition competition = getActualCompetition(competitionId);
        User user = utilityService.getContextUser();
        if(!user.equals(competition.getCompetitor()))  throw new PermissionDeniedDataAccessException("You have no right to delete this challenge", new Throwable());
        if(competition.getAccepted()) throw new IllegalArgumentException(String.format("This challenge: %d is already accepted", competitionId));
        competition.setAccepted(true);
        CompetitionDto competitionDto = CompetitionDto.fromCompetition(
                competitionRepository.save(competition)
        );
        logger.info("Competition with id: {} has been accepted by {}", competitionId, competition.getCompetitor().getUsername());
        return competitionDto;
    }

    public CompetitionDto deleteCompetition(Long competitionId) {
        Competition competition = getActualCompetition(competitionId);
        User user = utilityService.getContextUser();
        if(!competition.getChallenger().equals(user)) throw new PermissionDeniedDataAccessException("You have no right to delete this challenge", new Throwable());
        competitionRepository.delete(competition);
        logger.info("Competition with id: {} has been deleted", competitionId);
        return CompetitionDto.fromCompetition(competition);
    }
}

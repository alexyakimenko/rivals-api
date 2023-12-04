package com.rivals.rivalsapi.dto.competition;

import com.rivals.rivalsapi.dto.challenge.ChallengeDto;
import com.rivals.rivalsapi.dto.user.UserDto;
import com.rivals.rivalsapi.model.Challenge;
import com.rivals.rivalsapi.model.Competition;
import com.rivals.rivalsapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDto {
    private Long id;
    private UserDto challenger;
    private UserDto competitor;
    private ChallengeDto challenge;
    private Boolean accepted;

    public static CompetitionDto fromCompetition(Competition competition) {
        return CompetitionDto.builder()
                .id(competition.getId())
                .challenger(UserDto.fromUser(competition.getChallenger()))
                .competitor(UserDto.fromUser(competition.getCompetitor()))
                .challenge(ChallengeDto.fromChallenge(competition.getChallenge()))
                .accepted(competition.getAccepted())
                .build();
    }
}

package com.rivals.rivalsapi.dto.challenge;

import com.rivals.rivalsapi.model.Challenge;
import com.rivals.rivalsapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto {
    private Long id;
    private User creator;
    private LocalDateTime creationDate;
    private String title;
    private String description;

    public static ChallengeDto fromChallenge(Challenge challenge) {
        return ChallengeDto.builder()
                .id(challenge.getId())
                .creator(challenge.getCreator())
                .creationDate(challenge.getCreationDate())
                .title(challenge.getTitle())
                .description(challenge.getDescription())
                .build();
    }
}

package com.rivals.rivalsapi.dto.challenge;

import com.rivals.rivalsapi.model.Challenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddChallengeDto {
    private String title;
    private String description;

    public static Challenge toChallenge(AddChallengeDto challengeDto) {
        return Challenge.builder()
                .title(challengeDto.getTitle())
                .description(challengeDto.getDescription())
                .build();
    }
}

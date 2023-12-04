package com.rivals.rivalsapi.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCompetitionDto {
        private String targetUsername;
        private Long challenge_id;
}

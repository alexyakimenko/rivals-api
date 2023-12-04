package com.rivals.rivalsapi.dto.competition;

import com.rivals.rivalsapi.model.Challenge;
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
    private User challenger;
    private User competitor;
    private Challenge challenge;
    private Boolean accepted;
}

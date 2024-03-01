package com.rivals.rivalsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "challenger_id", referencedColumnName = "id")
    private User challenger;
    @ManyToOne
    @JoinColumn(name = "competitor_id", referencedColumnName = "id")
    private User competitor;
    @ManyToOne
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    private Challenge challenge;
    private Boolean accepted;
}

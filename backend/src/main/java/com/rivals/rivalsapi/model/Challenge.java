package com.rivals.rivalsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue
    private Long id;
    private User creator;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private String title;
    private String description;
}

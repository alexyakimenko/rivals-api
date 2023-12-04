package com.rivals.rivalsapi.repository;

import com.rivals.rivalsapi.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}

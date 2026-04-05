package com.fitness.app.repo;

import com.fitness.app.entity.DIetRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRecommendationRepo extends JpaRepository<DIetRecommendation, Long> {
}

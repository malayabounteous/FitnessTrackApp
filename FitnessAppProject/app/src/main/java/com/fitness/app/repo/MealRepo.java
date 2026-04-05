package com.fitness.app.repo;

import com.fitness.app.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
    List<Meal> findByUserId(long userId);

    List<Meal> findByUserIdAndDate(Long userId, LocalDate date);
}

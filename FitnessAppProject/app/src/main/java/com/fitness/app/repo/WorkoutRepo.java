package com.fitness.app.repo;
import java.time.LocalDate;
import java.util.List;
import com.fitness.app.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);

    List<Workout> findByUserIdAndDate(Long userId, LocalDate date);
}

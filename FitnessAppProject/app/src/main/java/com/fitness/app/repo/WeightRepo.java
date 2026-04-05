package com.fitness.app.repo;

import com.fitness.app.entity.WeightLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeightRepo extends JpaRepository<WeightLog,Long> {
    List<WeightLog> findByUserIdOrderByRecordedAtAsc(Long userId);


    WeightLog findByUserIdAndRecordedAt(Long userId, LocalDate date);
}

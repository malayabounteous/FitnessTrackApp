package com.fitness.app.DAO;

import com.fitness.app.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GoalDto {
    private String goalType;    // weight loss, muscle gain
    private double targetValue;
    private LocalDate startDate;
    private LocalDate endDate;
    private long userId;

}

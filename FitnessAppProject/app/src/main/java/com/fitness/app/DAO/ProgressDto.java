package com.fitness.app.DAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDto {
    private double weight;
    private int caloriesConsumed;
    private int caloriesBurned;
    private int netCalories;
    private LocalDate date;
    private long userId;

}

package com.fitness.app.DAO;

import lombok.Data;

import java.util.List;
@Data
public class ProgressResponseDTO {
    private List<ProgressDto> dailyStats;
    private int totalWeeklyConsumed;
    private int totalWeeklyBurned;
    private double weightChange;

    public ProgressResponseDTO(List<ProgressDto> dailyStats, int totalWeeklyConsumed, int totalWeeklyBurned, double weightChange) {
        this.dailyStats = dailyStats;
        this.totalWeeklyConsumed = totalWeeklyConsumed;
        this.totalWeeklyBurned = totalWeeklyBurned;
        this.weightChange=weightChange;
    }
}

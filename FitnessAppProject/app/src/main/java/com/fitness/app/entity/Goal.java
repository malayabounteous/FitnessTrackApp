package com.fitness.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String goalType;    // weight loss, muscle gain
    @Column(nullable = false)
    private double targetValue;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private User user;

    public Goal() {
    }

    public Goal(String goalType, double targetValue, LocalDate startDate, LocalDate endDate, User user) {
        this.goalType = goalType;
        this.targetValue = targetValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }
}

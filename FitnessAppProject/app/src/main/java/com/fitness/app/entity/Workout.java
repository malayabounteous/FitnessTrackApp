package com.fitness.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private int caloriesBurned;
    private LocalDate date;

    @ManyToOne
    private User user;
    public Workout(String type, int duration, int caloriesBurned, LocalDate date, User user) {
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
        this.user = user;
    }

    public Workout() {
    }

}

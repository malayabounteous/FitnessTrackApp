package com.fitness.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int calories;
    private int protein;
    private int carbs;
    private int fats;

    private String mealType;    // breakfast/lunch/dinner
    private LocalDate date;

    @ManyToOne
    private User user;

    public Meal() {
    }

    public Meal(String name, int calories, int protein, int carbs, int fats, String mealType, LocalDate date, User user) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.mealType = mealType;
        this.date = date;
        this.user = user;
    }
}

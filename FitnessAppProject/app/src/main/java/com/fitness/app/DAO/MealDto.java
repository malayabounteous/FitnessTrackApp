package com.fitness.app.DAO;

import com.fitness.app.entity.User;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MealDto {
    private String name;        // rice, chicken
    private int calories;
    private int protein;
    private int carbs;
    private int fats;

    private String mealType;    // breakfast/lunch/dinner
    private LocalDate date;
    private long userId;

}

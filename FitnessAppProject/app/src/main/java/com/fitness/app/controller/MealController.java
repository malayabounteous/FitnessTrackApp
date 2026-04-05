package com.fitness.app.controller;

import com.fitness.app.DAO.MealDto;
import com.fitness.app.entity.Meal;
import com.fitness.app.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin
public class MealController {

    @Autowired
    private MealService service;
    @PostMapping("/add")
    public ResponseEntity<Meal> addMeal(@RequestBody MealDto meal)
    {
        return ResponseEntity.ok(service.addMeal(meal));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Meal>> getMeals(@PathVariable long userId)
    {
        return ResponseEntity.ok(service.getMealsByUser(userId));
    }

    @GetMapping("/calories/{userId}")//get total calories for a day
    public ResponseEntity<Integer> getDailyCalories(@PathVariable long userId){
        return ResponseEntity.ok(service.calculateDailyCalories(userId));
    }
}

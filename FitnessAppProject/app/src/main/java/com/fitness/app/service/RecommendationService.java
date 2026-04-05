package com.fitness.app.service;

import com.fitness.app.entity.Meal;
import com.fitness.app.entity.User;
import com.fitness.app.entity.Workout;
import com.fitness.app.repo.MealRepo;
import com.fitness.app.repo.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private MealRepo mealRepo;
    @Autowired
    private WorkoutRepo workoutRepo;

    @Autowired
    private AIService aiService;
    public String getRecommendations(Long userId) {
        int caloriesConsumed = mealRepo.findByUserId(userId)
                .stream()
                .mapToInt(Meal::getCalories)
                .sum();

        int caloriesBurned = workoutRepo.findByUserId(userId)
                .stream()
                .mapToInt(Workout::getCaloriesBurned)
                .sum();

        String prompt = "User consumed " + caloriesConsumed +
                " calories and burned " + caloriesBurned +
                ". Suggest a personalized diet plan.";

        return aiService.getRecommendation(prompt);
    }
}

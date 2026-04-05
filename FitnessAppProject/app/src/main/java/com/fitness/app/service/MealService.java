package com.fitness.app.service;

import com.fitness.app.DAO.MealDto;
import com.fitness.app.entity.Meal;
import com.fitness.app.entity.User;
import com.fitness.app.exceptionHandler.UserNotFound;
import com.fitness.app.repo.MealRepo;
import com.fitness.app.repo.UserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepo repo;

    @Autowired
    private UserRepo userRepo;
    public @Nullable Meal addMeal(MealDto meal) {
        User user=userRepo.findById(meal.getUserId()).orElseThrow(()->new UserNotFound("user not found with id-"+meal.getUserId()));
        Meal m=new Meal(meal.getName(),meal.getCalories(),meal.getProtein(),meal.getCarbs(),meal.getFats(),meal.getMealType(),meal.getDate(),user);
        return repo.save(m);
    }
    public java.util.List<Meal> getMealsByUser(long userId) {
        return repo.findByUserId(userId);
    }
    public int calculateDailyCalories(long userId) {
        List<Meal> meals=repo.findByUserId(userId);

        int ans=meals.stream().mapToInt(Meal::getCalories).sum();
        return ans;
    }
}

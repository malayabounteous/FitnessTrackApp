package com.fitness.app.service;

import com.fitness.app.DAO.ProgressDto;
import com.fitness.app.DAO.ProgressResponseDTO;
import com.fitness.app.entity.Meal;
import com.fitness.app.entity.User;
import com.fitness.app.entity.WeightLog;
import com.fitness.app.entity.Workout;
import com.fitness.app.exceptionHandler.UserNotFound;
import com.fitness.app.repo.MealRepo;
import com.fitness.app.repo.UserRepo;
import com.fitness.app.repo.WeightRepo;
import com.fitness.app.repo.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProgressService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MealRepo mealRepo;
    @Autowired
    private WorkoutRepo workoutRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private WeightRepo weightRepo;
    public ProgressResponseDTO getWeeklyStats(Long userId) {

        User user=userRepo.findById(userId).orElseThrow(()->new UserNotFound("user not found with id-"+userId));
        if(user==null)return null;
        List<ProgressDto> weeklyStats = new ArrayList<>();
        LocalDate today = LocalDate.now();
        int totalConsumed = 0;
        int totalBurned = 0;
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);

            // Sum calories from meals for this day
            int consumed = mealRepo.findByUserIdAndDate(userId, date)
                    .stream().mapToInt(Meal::getCalories).sum();
             totalConsumed+=consumed;
            // Sum calories from workouts for this day
            WeightLog w=weightRepo.findByUserIdAndRecordedAt(userId,date);
            double weight=w!=null?w.getWeight():user.getWeight();

            int burned = workoutRepo.findByUserIdAndDate(userId, date)
                    .stream().mapToInt(Workout::getCaloriesBurned).sum();
            totalBurned+=burned;
            weeklyStats.add(new ProgressDto(weight, consumed, burned, consumed - burned,date,userId));
        }
        double weightChange=userService.updateWeightBasedOnCalories(userId,totalConsumed,totalBurned);
        ProgressResponseDTO ans=new ProgressResponseDTO(weeklyStats,totalConsumed,totalBurned,weightChange);
        return ans;
    }
}

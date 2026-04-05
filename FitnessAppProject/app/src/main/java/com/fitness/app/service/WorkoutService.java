package com.fitness.app.service;

import com.fitness.app.DAO.WorkoutReq;
import com.fitness.app.entity.User;
import com.fitness.app.entity.Workout;
import com.fitness.app.exceptionHandler.UserNotFound;
import com.fitness.app.repo.UserRepo;
import com.fitness.app.repo.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepo workoutRepo;
    @Autowired
    private UserRepo userRepo;

    public Workout addWorkout(WorkoutReq workout) {
        User user=userRepo.findById(workout.getUserId()).orElseThrow(()->new UserNotFound("user not found"));
        Workout w=new Workout(workout.getType(),workout.getDuration(),workout.getCaloriesBurned(),workout.getDate(),user);
        int calories=calculateCalories(workout.getType(),workout.getDuration());
        workout.setCaloriesBurned(calories);
        return workoutRepo.save(w);
    }
    public List<Workout> getWorkoutsByUserId(long id) {

        return workoutRepo.findByUserId(id);
    }
    public void deleteWorkout(long id) {
        workoutRepo.deleteById(id);
    }
    private int calculateCalories(String type,int duration){
        switch (type.toLowerCase()){
            case "running":
                return duration*10;
            case "cycling":
                return duration*8;
            case "gym":
                return duration*6;
            case "yoga":
                return duration*4;
            default:
                return duration*5;
        }
    }

}

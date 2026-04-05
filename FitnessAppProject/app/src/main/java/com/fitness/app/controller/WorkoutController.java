package com.fitness.app.controller;

import com.fitness.app.DAO.WorkoutReq;
import com.fitness.app.DAO.WorkoutRes;
import com.fitness.app.entity.Workout;
import com.fitness.app.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    //add workout
    @PostMapping
    public ResponseEntity<Workout> addWorkout(@RequestBody WorkoutReq workoutReq)
    {
        Workout savedWorkout = workoutService.addWorkout(workoutReq);
        return ResponseEntity.ok(savedWorkout);
    }
    @GetMapping("/user/{userId}")//get workout by a specific user
    public ResponseEntity<List<Workout>> getUserWorkouts(@PathVariable long userId)
    {
        return  ResponseEntity.ok(workoutService.getWorkoutsByUserId(userId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable long id)
    {
        workoutService.deleteWorkout(id);
        return ResponseEntity.ok("Workout deleted successfully");
    }

}

package com.fitness.app.controller;

import com.fitness.app.DAO.GoalDto;
import com.fitness.app.entity.Goal;
import com.fitness.app.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin
public class GoalController {
    @Autowired
    private GoalService service;

    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody GoalDto goalDto) {
        Goal goal = service.createGoal(goalDto);
        return ResponseEntity.ok(goal);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Goal>> getGoalsByUserId(@PathVariable Long userId) {
        List<Goal> goals = service.getGoalsByUserId(userId);
        return ResponseEntity.ok(goals);
    }

}

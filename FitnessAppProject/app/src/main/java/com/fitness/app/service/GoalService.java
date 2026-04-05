package com.fitness.app.service;

import com.fitness.app.DAO.GoalDto;
import com.fitness.app.entity.Goal;
import com.fitness.app.entity.User;
import com.fitness.app.exceptionHandler.UserNotFound;
import com.fitness.app.repo.GoalRepo;
import com.fitness.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoalService {

    @Autowired
    private GoalRepo repo;
    @Autowired
    private UserRepo userRepo;
    public Goal createGoal(GoalDto goalDto) {
        User user=userRepo.findById(goalDto.getUserId()).orElseThrow(()->new UserNotFound("user not found with id-"+goalDto.getUserId()));
        Goal goal=new Goal(goalDto.getGoalType(),goalDto.getTargetValue(),goalDto.getStartDate(),goalDto.getEndDate(),user);

        return repo.save(goal);
    }

    public List<Goal> getGoalsByUserId(Long userId) {

        return repo.findByUserId(userId);
    }
}

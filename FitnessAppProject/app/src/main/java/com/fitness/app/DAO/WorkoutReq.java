package com.fitness.app.DAO;

import com.fitness.app.entity.User;
import lombok.Data;

import java.time.LocalDate;
@Data
public class WorkoutReq {

    private String type;        // running, gym, yoga
    private int duration;       // minutes
    private int caloriesBurned;
    private LocalDate date;
    private long userId;

}

package com.fitness.app.controller;

import com.fitness.app.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin
public class ProgressController {
    @Autowired
    private ProgressService service;

    @GetMapping("/weekly/{userId}")
    public ResponseEntity<?> getWeeklyStats(@PathVariable long userId) {
        return ResponseEntity.ok(service.getWeeklyStats(userId));
    }
}

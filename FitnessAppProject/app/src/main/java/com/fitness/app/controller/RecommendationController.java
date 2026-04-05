package com.fitness.app.controller;

import com.fitness.app.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin
public class RecommendationController {
    @Autowired
    private RecommendationService service;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getRecommendations(@PathVariable long userId) {
        String recommendations = service.getRecommendations( userId);
        return ResponseEntity.ok(Map.of("data",recommendations));
    }
}

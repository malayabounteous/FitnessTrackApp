package com.fitness.app.controller;

import com.fitness.app.DAO.UserDto;
import com.fitness.app.entity.User;
import com.fitness.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto)
    {
        User user = service.register(userDto);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){

        String token = service.login(userDto.getEmail(), userDto.getPassword());

        return ResponseEntity.ok(Map.of("token",token));
    }

}

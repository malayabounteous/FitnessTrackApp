package com.fitness.app.service;

import com.fitness.app.DAO.UserDto;
import com.fitness.app.entity.User;
import com.fitness.app.entity.WeightLog;
import com.fitness.app.exceptionHandler.UserNotFound;
import com.fitness.app.repo.UserRepo;
import com.fitness.app.repo.WeightRepo;
import com.fitness.app.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WeightRepo weightRepo;
    public User register(UserDto userDto) {
        User user=new User(userDto.getName(),userDto.getEmail(),userDto.getPassword(),userDto.getAge(),userDto.getHeight(),userDto.getWeight());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String login(String email, String password) {
        User user = repo.findByEmail(email);
        if(user==null)return "user not found try with differenet credentials";

         boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());

        if (user != null && isPasswordMatch) {
            return jwtUtil.generateToken(user.getEmail(),user.getId(), user.getName());
        }
        return "password not matched or user not found try with differenet credentials"; // or throw an exception for invalid credentials
    }
    public double updateWeightBasedOnCalories(Long userId, double consumed, double burned) {
        User user = repo.findById(userId).orElseThrow(()->new UserNotFound("user not found with id-"+userId));

        double netCalories = consumed - burned;

        // Calculate weight change (7700 kcal = 1kg)
        double weightChange = netCalories / 7700.0;

        // Update the user's current weight
        double newWeight = user.getWeight() + weightChange;

        // Round to 2 decimal places for a "Pro" look (e.g., 75.12 kg)
        newWeight = Math.round(newWeight * 100.0) / 100.0;

        user.setWeight(newWeight);
        LocalDate today = LocalDate.now();
        // Try to find if a record already exists for today
        Optional<WeightLog> existing = Optional.ofNullable(weightRepo.findByUserIdAndRecordedAt(userId, today));

        if (existing.isPresent()) {
            WeightLog log = existing.get();
            log.setWeight(newWeight);
            weightRepo.save(log); // This updates the existing row
        } else {
            WeightLog newLog = new WeightLog();
            newLog.setUserId(userId);
            newLog.setWeight(newWeight);
            newLog.setRecordedAt(today);
            weightRepo.save(newLog); // This inserts a new row
        }
        repo.save(user);
        return weightChange;
    }
}

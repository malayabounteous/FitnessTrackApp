package com.fitness.app.DAO;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private int age;
    private double height;
    private double weight;

}

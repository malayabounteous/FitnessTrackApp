package com.fitness.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="fitness_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int age;
    private double height;
    private double weight;

    public User() {
    }

    public User(String name, String email, String password, int age, double height, double weight) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
}

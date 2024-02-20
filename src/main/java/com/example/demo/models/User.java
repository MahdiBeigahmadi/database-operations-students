package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String weight;
    private String height;
    private String hairColor;
    private String gpa;

    public User() {
    }

    public User(String name, String weight,
            String height, String hairColor, String gpa) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpaUpdate) {
        this.gpa = gpaUpdate;
    }
}

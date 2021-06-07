package com.techelevator.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Meal {
    private int meal_id;
    private int user_id;
    private List<Food> food;
    private int totalCals;
    private int totalProtein;
    private int totalCarbs;
    private int totalFats;

    private Date date;
    private String typeOfMeal;


    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public int getTotalCals() {
        return totalCals;
    }

    public void setTotalCals(int totalCals) {
        this.totalCals = totalCals;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public int getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(int totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public int getTotalFats() {
        return totalFats;
    }

    public void setTotalFats(int totalFats) {
        this.totalFats = totalFats;
    }
}

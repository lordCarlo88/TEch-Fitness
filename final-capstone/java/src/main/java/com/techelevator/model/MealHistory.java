package com.techelevator.model;

import java.util.Date;

public class MealHistory {
    private int user_id;
    private int meal_id;
    private String food;
    private int servings;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private String typeOfMeal;
    private Date dateOfMeal;

    public MealHistory() {

    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public Date getDateOfMeal() {
        return dateOfMeal;
    }

    public void setDateOfMeal(Date dateOfMeal) {
        this.dateOfMeal = dateOfMeal;
    }
}

package com.techelevator.model;

public class Food {
    private int food_id;
    private int meal_id;
    private String name;
    private int serving;
    private int fats;
    private int proteins;
    private int carbs;
    private int cals;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getMeal_id(){
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getCals() {
        return cals;
    }

    public void setCals(int cals) {
        this.cals = cals;
    }
}

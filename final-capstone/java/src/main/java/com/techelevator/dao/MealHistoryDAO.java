package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.Meal;
import com.techelevator.model.MealHistory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public interface MealHistoryDAO {

    public void addMeal(MealHistory mealHistory);

    public int getMealId(int id, String typeOfMeal, String date);

    public void updateCalories(Meal meal);

    public void logFood(Food food);

    public void logMeal(Meal meal);

    public Meal getMealHistoryByBreakfast(int id, String date);

    public Meal getMealHistoryByLunch(int id, String date);

    public Meal getMealHistoryByDinner(int id, String date);

    public Meal getMealHistoryBySnack(int id, String date);

    public void updateMeal(MealHistory mealHistory);

    public void deleteMeal(int meal_id);


}

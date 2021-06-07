package com.techelevator.model;

public class WorkoutHistory {
    private int userID;
    private String typeOfWorkout;
    private int time;
    private int caloriesBurned;

    public WorkoutHistory() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTypeOfWorkout() {
        return typeOfWorkout;
    }

    public void setTypeOfWorkout(String typeOfWorkout) {
        this.typeOfWorkout = typeOfWorkout;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}

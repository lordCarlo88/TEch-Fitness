package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.Meal;
import com.techelevator.model.MealHistory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class MealHistorySqlDAO implements MealHistoryDAO {
    private JdbcTemplate jdbcTemplate;


    public MealHistorySqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMeal(MealHistory mealHistory) {
        String sql = "INSERT INTO meal (user_id, food, servings, calories, protein, carbs, fat) " + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, mealHistory.getUser_id(),mealHistory.getFood(), mealHistory.getServings(), mealHistory.getCalories(),
                mealHistory.getProtein(), mealHistory.getCarbs(), mealHistory.getFat());
        String sql1 = "INSERT INTO mealhistory (user_id, meal_id, typeofmeal, date) " +
                "VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql1, mealHistory.getUser_id(), mealHistory.getMeal_id(), mealHistory.getTypeOfMeal(), mealHistory.getDateOfMeal());
    }

    @Override
    public int getMealId(int id, String typeOfMeal, String date) {
        System.out.println("the date" + date);
        LocalDate localdate = LocalDate.parse(date);
        System.out.println(localdate);
        System.out.println(id);
        System.out.println(typeOfMeal);
        String sql = "select meal.meal_id from meal where meal.user_id = ? and meal.typeofmeal = ? and meal.date = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, typeOfMeal, localdate);

        List<Integer> mealIdList = new ArrayList<Integer>();

        while(results.next()){
            int mealId = results.getInt("meal_id");

            mealIdList.add(mealId);

        }
        Collections.sort(mealIdList);
        Collections.reverse(mealIdList);

        return mealIdList.get(0);
    }

    @Override
    public void updateCalories(Meal meal) {
        String sql = "update meal set totalcals = ?, totalprotein = ?, totalcarbs = ?, totalfats = ?  where meal_id = ?";



    }


    @Override
    public void logFood(Food food) {
        String sql = "insert into food ( meal_id, name, serving, fats, proteins, carbs, cals) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, food.getMeal_id(), food.getName(), food.getServing(), food.getFats(), food.getProteins(), food.getCarbs(), food.getCals());

    }

    @Override
    public void logMeal(Meal meal) {
        String sql = "insert into meal ( user_id, totalcals, totalprotein, totalcarbs, totalfats, date, typeofmeal) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, meal.getUser_id(), meal.getTotalCals(), meal.getTotalProtein(), meal.getTotalCarbs(), meal.getTotalFats(), meal.getDate(), meal.getTypeOfMeal());
    }






    @Override
    public Meal getMealHistoryByBreakfast(int id, String date) {

        List<Food> foodList = new ArrayList<Food>();

        Meal meal = new Meal();
        LocalDate localdate = LocalDate.parse(date);
        String sql = "Select meal.meal_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id WHERE meal.user_id = ? AND typeofmeal = 'Breakfast' AND date = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id, localdate);


        while (result.next()) {
            Food food = new Food();
            food.setName(result.getString("name")) ;
            food.setServing(result.getInt("serving"));
            food.setFats(result.getInt("fats"));
            food.setProteins(result.getInt("proteins"));
            food.setCarbs(result.getInt("carbs"));
            food.setCals(result.getInt("cals"));

            foodList.add(food);



            meal.setMeal_id(result.getInt("meal_id"));
            meal.setDate(result.getDate("date"));
            meal.setTypeOfMeal(result.getString("typeofmeal"));

        }
        int cals = 0;
        int proteins = 0;
        int fats = 0;
        int carbs = 0;

        for (int i = 0;i <foodList.size();i+=1){
            cals += foodList.get(i).getCals();
            proteins += foodList.get(i).getProteins();
            fats += foodList.get(i).getFats();
            carbs += foodList.get(i).getCarbs();
        }
        meal.setTotalCals(cals);
        meal.setTotalProtein(proteins);
        meal.setTotalFats(fats);
        meal.setTotalCarbs(carbs);

        System.out.println("this is working on this end");
        System.out.println("this is the date: " + date);
        meal.setFood(foodList);

        String sql2 = "update meal set totalcals = ?, totalprotein = ?, totalcarbs = ?, totalfats = ?  where meal_id = ?";
        jdbcTemplate.update(sql2,cals,proteins,carbs,fats,meal.getMeal_id());
        return meal;
    }



    @Override
    public Meal getMealHistoryByLunch(int id, String date) {
        List<Food> foodList = new ArrayList<Food>();

        Meal meal = new Meal();
        LocalDate localdate = LocalDate.parse(date);
        String sql = "Select meal.meal_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id WHERE meal.user_id = ? AND typeofmeal = 'Lunch' AND date = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id, localdate);


        while (result.next()) {
            Food food = new Food();
            food.setName(result.getString("name")) ;
            food.setServing(result.getInt("serving"));
            food.setFats(result.getInt("fats"));
            food.setProteins(result.getInt("proteins"));
            food.setCarbs(result.getInt("carbs"));
            food.setCals(result.getInt("cals"));

            foodList.add(food);



            meal.setMeal_id(result.getInt("meal_id"));
            meal.setDate(result.getDate("date"));
            meal.setTypeOfMeal(result.getString("typeofmeal"));

        }
        int cals = 0;
        int proteins = 0;
        int fats = 0;
        int carbs = 0;

        for (int i = 0;i <foodList.size();i+=1){
            cals += foodList.get(i).getCals();
            proteins += foodList.get(i).getProteins();
            fats += foodList.get(i).getFats();
            carbs += foodList.get(i).getCarbs();
        }
        meal.setTotalCals(cals);
        meal.setTotalProtein(proteins);
        meal.setTotalFats(fats);
        meal.setTotalCarbs(carbs);

        System.out.println("this is working on this end");
        System.out.println("this is the date: " + date);
        meal.setFood(foodList);
        String sql2 = "update meal set totalcals = ?, totalprotein = ?, totalcarbs = ?, totalfats = ?  where meal_id = ?";
        jdbcTemplate.update(sql2,cals,proteins,carbs,fats,meal.getMeal_id());
        return meal;
    }



    @Override
    public Meal getMealHistoryByDinner(int id, String date) {
        List<Food> foodList = new ArrayList<Food>();

        Meal meal = new Meal();
        LocalDate localdate = LocalDate.parse(date);
        String sql = "Select meal.meal_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id WHERE meal.user_id = ? AND typeofmeal = 'Dinner' AND date = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id, localdate);


        while (result.next()) {
            Food food = new Food();
            food.setName(result.getString("name")) ;
            food.setServing(result.getInt("serving"));
            food.setFats(result.getInt("fats"));
            food.setProteins(result.getInt("proteins"));
            food.setCarbs(result.getInt("carbs"));
            food.setCals(result.getInt("cals"));

            foodList.add(food);



            meal.setMeal_id(result.getInt("meal_id"));
            meal.setDate(result.getDate("date"));
            meal.setTypeOfMeal(result.getString("typeofmeal"));

        }
        int cals = 0;
        int proteins = 0;
        int fats = 0;
        int carbs = 0;

        for (int i = 0;i <foodList.size();i+=1){
            cals += foodList.get(i).getCals();
            proteins += foodList.get(i).getProteins();
            fats += foodList.get(i).getFats();
            carbs += foodList.get(i).getCarbs();
        }
        meal.setTotalCals(cals);
        meal.setTotalProtein(proteins);
        meal.setTotalFats(fats);
        meal.setTotalCarbs(carbs);

        System.out.println("this is working on this end");
        System.out.println("this is the date: " + date);
        meal.setFood(foodList);

        String sql2 = "update meal set totalcals = ?, totalprotein = ?, totalcarbs = ?, totalfats = ?  where meal_id = ?";
        jdbcTemplate.update(sql2,cals,proteins,carbs,fats,meal.getMeal_id());
        return meal;
    }

    @Override
    public Meal getMealHistoryBySnack(int id, String date) {
        List<Food> foodList = new ArrayList<Food>();

        Meal meal = new Meal();
        LocalDate localdate = LocalDate.parse(date);
        String sql = "Select meal.meal_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id WHERE meal.user_id = ? AND typeofmeal = 'Snack' AND date = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id, localdate);


        while (result.next()) {
            Food food = new Food();
            food.setName(result.getString("name")) ;
            food.setServing(result.getInt("serving"));
            food.setFats(result.getInt("fats"));
            food.setProteins(result.getInt("proteins"));
            food.setCarbs(result.getInt("carbs"));
            food.setCals(result.getInt("cals"));

            foodList.add(food);



            meal.setMeal_id(result.getInt("meal_id"));
            meal.setDate(result.getDate("date"));
            meal.setTypeOfMeal(result.getString("typeofmeal"));

        }
        int cals = 0;
        int proteins = 0;
        int fats = 0;
        int carbs = 0;

        for (int i = 0;i <foodList.size();i+=1){
            cals += foodList.get(i).getCals();
            proteins += foodList.get(i).getProteins();
            fats += foodList.get(i).getFats();
            carbs += foodList.get(i).getCarbs();
        }
        meal.setTotalCals(cals);
        meal.setTotalProtein(proteins);
        meal.setTotalFats(fats);
        meal.setTotalCarbs(carbs);

        System.out.println("this is working on this end");
        System.out.println("this is the date: " + date);
        meal.setFood(foodList);
        String sql2 = "update meal set totalcals = ?, totalprotein = ?, totalcarbs = ?, totalfats = ?  where meal_id = ?";
        jdbcTemplate.update(sql2,cals,proteins,carbs,fats,meal.getMeal_id());
        return meal;
    }


    @Override
    public void updateMeal(MealHistory mealHistory) {
        String sql = "UPDATE meal SET food = ?, servings = ? WHERE food = ? AND meal_id = ?";
        jdbcTemplate.update(sql, mealHistory.getFood(), mealHistory.getServings(), mealHistory.getFood(), mealHistory.getMeal_id());
    }

    @Override
    public void deleteMeal(int meal_id) {
        String sql = "DELETE FROM meal WHERE meal_id = ?";
        jdbcTemplate.update(sql, meal_id);
        String sql1 = "DELETE FROM mealhistory WHERE meal_id = ?";
        jdbcTemplate.update(sql, meal_id);
    }

    private MealHistory mapRowToMealHistory(SqlRowSet rowSet) {
        MealHistory mealHistory = new MealHistory();
       mealHistory.setUser_id(rowSet.getInt("user_id"));
       mealHistory.setMeal_id(rowSet.getInt("meal_id"));
       mealHistory.setFood(rowSet.getString("food"));
       mealHistory.setServings(rowSet.getInt("servings"));
       mealHistory.setCalories(rowSet.getInt("calories"));
       mealHistory.setProtein(rowSet.getInt("protein"));
       mealHistory.setCarbs(rowSet.getInt("carbs"));
       mealHistory.setFat(rowSet.getInt("fat"));
       mealHistory.setTypeOfMeal(rowSet.getString("typeofmeal"));
       mealHistory.setDateOfMeal(rowSet.getDate("date"));
       return mealHistory;
    }
}

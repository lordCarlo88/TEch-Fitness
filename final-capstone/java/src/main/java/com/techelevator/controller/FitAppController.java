package com.techelevator.controller;

import com.techelevator.dao.MealHistoryDAO;
import com.techelevator.dao.ProfileDAO;
import com.techelevator.model.Food;
import com.techelevator.model.Meal;
import com.techelevator.model.MealHistory;
import com.techelevator.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

//import org.springframework.context.annotation.Profile;



@RestController
@CrossOrigin("*")
@PreAuthorize("isAuthenticated()")
public class FitAppController {

    @Autowired
    private ProfileDAO profileDAO;
    @Autowired
    private MealHistoryDAO mealHistoryDAO;




    @RequestMapping(path = "/createProfile", method = RequestMethod.POST)
    public void createProfile(@RequestBody Profile profile) throws Exception {

        int userId = profile.getUser_id();
        // check to see if user exists:
        Profile newProfile = profileDAO.findIfUserExists(userId);
        if (newProfile.getUser_id() != 0) {
            System.out.println("exists");
            throw new Exception();
        }

        profileDAO.createProfile(profile);

    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public Profile viewProfile(@PathVariable int id) {
        Profile profile;
        profile = profileDAO.viewProfile(id);
        return profile;
    }

    @RequestMapping(path = "/editProfile", method = RequestMethod.PUT)
    public void editProfile(@RequestBody Profile profile) {
        profileDAO.editProfile(profile);
    }

    @RequestMapping(path = "/trackBreakfast/{id}", method = RequestMethod.GET)
    public Meal getMealHistoryByBreakfast(@PathVariable ("id")int userID, @RequestParam String date) {
        System.out.println("this is working");
        System.out.println();

        Meal meal = mealHistoryDAO.getMealHistoryByBreakfast(userID, date);
       return meal;
    }

    @RequestMapping(path = "/trackLunch/{id}", method = RequestMethod.GET)
    public Meal getMealHistoryByLunch(@PathVariable ("id")int userID, @RequestParam String date) {

        Meal meal = mealHistoryDAO.getMealHistoryByLunch(userID, date);
        return meal;
    }

    @RequestMapping(path = "/trackDinner/{id}", method = RequestMethod.GET)
    public Meal getMealHistoryByDinner(@PathVariable ("id")int userID, @RequestParam String date) {

        Meal meal = mealHistoryDAO.getMealHistoryByDinner(userID, date);
        return meal;
    }

    @RequestMapping(path = "/trackSnacks/{id}", method = RequestMethod.GET)
    public Meal getMealHistoryBySnack(@PathVariable ("id")int userID, @RequestParam String date) {

        Meal meal = mealHistoryDAO.getMealHistoryBySnack(userID, date);
        return meal;
    }


//    @RequestMapping(path = "/findprofile/{id}", method = RequestMethod.GET)
//    public boolean findIfProfileExists(@PathVariable int userId) {
//        Profile profile = new Profile();
//        profile = profileDAO.findIfUserExists(userId);
//        if(profile == null) {
//            return false;
//        }
//        return true;
//    }
    @RequestMapping(path = "/getMealId/{id}/{typeofmeal}", method = RequestMethod.GET)
    public int getMealId (@PathVariable ("id") int userId, @PathVariable ("typeofmeal") String typeofmeal, @RequestParam String date){

        int mealId = mealHistoryDAO.getMealId(userId,typeofmeal,date);

        return mealId;

    }


    @RequestMapping(path = "/addFood", method = RequestMethod.POST)
    public void addFood(@RequestBody Food food) {
        mealHistoryDAO.logFood(food);
    }


    @RequestMapping(path = "/addMeal", method = RequestMethod.POST)
    public int addMeal(@RequestBody Meal meal) {

        Date currentDate = new Date();
        meal.setDate(currentDate);

        LocalDateTime test = new Timestamp(currentDate.getTime()).toLocalDateTime();

        String monthNumber = new Integer(test.getMonthValue()).toString();
        monthNumber = monthNumber.length() == 1 ? "0" + monthNumber : monthNumber;

        String dayNumber = new Integer(test.getDayOfMonth()).toString();
        dayNumber = dayNumber.length() == 1 ? "0" + dayNumber : dayNumber;

        String dtString = test.getYear() + "-" + monthNumber + "-" +dayNumber;

        mealHistoryDAO.logMeal(meal);


        int mealId = mealHistoryDAO.getMealId(meal.getUser_id(), meal.getTypeOfMeal(),dtString);
        System.out.println("****");
        System.out.println(mealId);

        System.out.println(meal.getDate());

        return mealId;

    }

    @RequestMapping(path = "/updateMeal", method = RequestMethod.PUT)
    public void updateMeal(@RequestBody MealHistory mealHistory) {
        mealHistoryDAO.updateMeal(mealHistory);
    }

    @RequestMapping(path = "/deleteMeal", method = RequestMethod.DELETE)
    public void deleteMeal(@PathVariable int mealID) {
        mealHistoryDAO.deleteMeal(mealID);
    }





}

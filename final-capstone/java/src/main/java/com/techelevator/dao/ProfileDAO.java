package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDAO {

    public Profile viewProfile(int id);

    public void createProfile(Profile profile);

    public void editProfile(Profile profile);

    public void updateWeight(int id, int newWeight);

    public Profile findIfUserExists(int userId);
}

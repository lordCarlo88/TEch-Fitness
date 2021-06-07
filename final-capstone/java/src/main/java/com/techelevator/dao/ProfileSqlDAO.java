package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;



@Component
public class ProfileSqlDAO implements ProfileDAO{
private JdbcTemplate jdbcTemplate;

public ProfileSqlDAO(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
}

    @Override
    public Profile viewProfile(int id) {
            Profile profile = new Profile();
        String sql = "SELECT user_id, height, dateofbirth, gender, activitylevel, currentweight, desiredweight, firstname, lastname, profilepicture" +
                " FROM profile WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()) {
            profile = mapRowToProfile(result);
        }
        return profile;

    }

    @Override
    public void createProfile(Profile profile) {

        System.out.println(profile.getDateOfBirth());

        String sql = "INSERT INTO profile (user_id, height, dateofbirth, gender, activitylevel, currentweight, desiredweight, firstname, lastname, profilepicture)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(profile.getDateOfBirth() + profile.getFirstName() + profile.getLastName());
        jdbcTemplate.update(sql, profile.getUser_id(), profile.getHeight(), profile.getDateOfBirth(), profile.getGender(), profile.getActivityLevel(), profile.getCurrentWeight(), profile.getDesiredWeight(), profile.getFirstName(),

                profile.getLastName(), profile.getProfilePicture());
    }



    @Override
    public void editProfile(Profile profile) {
        String sql = "UPDATE profile SET height = ?, dateofbirth = ?, gender = ?, activitylevel = ?, currentweight = ?, desiredweight = ?, firstname = ?, lastname = ?, " +
                "profilepicture = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, profile.getHeight(), profile.getDateOfBirth(), profile.getGender(), profile.getActivityLevel(), profile.getCurrentWeight(), profile.getDesiredWeight(),
                profile.getFirstName(), profile.getLastName(), profile.getProfilePicture(), profile.getUser_id());
    }

    @Override
    public void updateWeight(int id, int newWeight) {
        String sql = "UPDATE profile SET currentweight = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, newWeight, id);
    }

    @Override
    public Profile findIfUserExists(int userId) {
        Profile profile = new Profile();
        String sql = "SELECT * FROM profile WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()) {
            profile = mapRowToProfile(result);
        }
        return profile;
    }

    private int getUserIdByName(String username) {
    String sql = "SELECT user_id FROM users WHERE username = ?";
    SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
    while (result.next()) {
        return result.getInt("user_id");
    }
    return -1;
    }



    private Profile mapRowToProfile(SqlRowSet rowSet) {
    Profile profile = new Profile();
    profile.setUser_id(rowSet.getInt("user_id"));
    profile.setHeight(rowSet.getInt("height"));
    profile.setDateOfBirth(rowSet.getDate("dateofbirth"));
    profile.setGender(rowSet.getString("gender"));
    profile.setActivityLevel(rowSet.getString("activitylevel"));
    profile.setCurrentWeight(rowSet.getInt("currentweight"));
    profile.setDesiredWeight(rowSet.getInt("desiredweight"));
    profile.setFirstName(rowSet.getString("firstname"));
    profile.setLastName(rowSet.getString("lastname"));
    profile.setProfilePicture(rowSet.getString("profilepicture"));
    return profile;
    }
}

START TRANSACTION;
 DROP TABLE IF EXISTS profile;

 DROP TABLE IF EXISTS meal;

 DROP TABLE IF EXISTS mealhistory;
 
 DROP TABLE IF EXISTS workouthistory;

CREATE TABLE meal
(
        meal_id serial,
        user_id int NOT NULL,
        food varchar(64) NOT NULL,
        servings varchar(100) NOT NULL,
        calories int NOT NULL,
        protein int NOT NULL,
        carbs int NOT NULL,
        fat int NOT NULL,

        CONSTRAINT pk_customer PRIMARY KEY (meal_id),
        CONSTRAINT fk_meal_user FOREIGN KEY (user_id) REFERENCES users (user_id)
 );

 CREATE TABLE mealhistory
 (
        history_id serial,
        user_id int NOT NULL,
        meal_id int NOT NULL,
        typeofmeal varchar(16) NOT NULL,
        date DATE NOT NULL,

        CONSTRAINT pk_customer_id PRIMARY KEY (history_id),
        CONSTRAINT fk_meal_user FOREIGN KEY (user_id) REFERENCES users (user_id),
        CONSTRAINT fk_meal_id FOREIGN KEY (meal_id) REFERENCES meal (meal_id)

 );


 CREATE TABLE profile
 (
        profile_id serial,
        user_id int NOT NULL,
        height int NOT NULL,
        dateofbirth DATE NOT NULL,
        gender varchar(10) NOT NULL,
        activitylevel varchar(30) NOT NULL,
        currentweight int NOT NULL,
        desiredweight int NOT NULL,
        firstname varchar(64) NOT NULL,
        lastname varchar(64) NOT NULL,
        profilepicture varchar(128),
        
        CONSTRAINT pk_profile_id PRIMARY KEY (profile_id),
        CONSTRAINT fk_user_user_info FOREIGN KEY (user_id) REFERENCES users (user_id)
  );
  
  CREATE TABLE workouthistory
  (
  workout_id serial,
  user_id int NOT NULL,
  type varchar(64) NOT NULL,
  time int NOT NULL,
  caloriesburned int NOT NULL,
  
  CONSTRAINT pk_workout_id PRIMARY KEY (workout_id),
  CONSTRAINT fk_user_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
  
  );
  
  
  COMMIT TRANSACTION;
 
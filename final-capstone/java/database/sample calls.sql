insert into meal ( user_id, totalcals, totalprotein, totalcarbs, totalfats, date, typeofmeal) 
values (3, 120, 120, 120, 120, '1988-10-10', 'Breakfast');

Select meal.meal_id, meal.user_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id where meal.typeofmeal = 'Breakfast';

Select meal.meal_id,  meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal,  
food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals
from meal
join food on food.meal_id = meal.meal_id
WHERE meal.user_id = 3 AND typeofmeal = 'Breakfast' AND date = '1988-10-10'; 

Select meal.meal_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id WHERE meal.user_id = 3 AND typeofmeal = 'Breakfast' AND date = '1988-10-10';

Select meal.meal_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id WHERE meal.user_id = 3 AND typeofmeal = 'Breakfast' AND date = '1988-10-10';

Select meal.meal_id, food.name, food.serving, food.fats, food.proteins, food.carbs, food.cals, meal.totalcals, meal.totalprotein, meal.totalcarbs, meal.totalfats, meal.date, meal.typeofmeal from meal join food on food.meal_id = meal.meal_id WHERE meal.user_id = 3 AND typeofmeal = 'Lunch' AND date = '1988-10-10';

select meal.meal_id
from meal 
where meal.typeofmeal = 'Dinner' and meal.date = '2021-06-02'; 
select meal.meal_id from meal where meal.user_id = 3 and meal.typeofmeal = 'Dinner' and meal.date = '2021-06-02';

update meal set totalcals = 39, totalprotein = 39, totalcarbs = 0, totalfats = 390  where meal_id = 13;


alter table meal
add column date date;

alter table meal 
add column typeofmeal varchar;

alter table meal 
drop column food;

alter table meal
drop column servings;

alter table meal 
rename column calories
to totalCals;

alter table meal
rename column protein 
to totalProtein;

alter table meal 
rename column carbs
to totalCarbs;

alter table meal 
rename column fat
to totalFats;

Create table food(

food_id serial Primary key,
meal_id int  not null,
name varchar  not null,
serving int  not null, 
fats int  not null, 
proteins int  not null, 
carbs int  not null, 
cals int  not null,

constraint fk_meal_id foreign key (meal_id) references meal(meal_id)

);



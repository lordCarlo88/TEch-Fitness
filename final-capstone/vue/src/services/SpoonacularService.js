import axios from 'axios';


const http = axios.create({
    baseURL: "https://api.spoonacular.com/recipes"
});

export default {
    searchRecipes(searchTerm) {
        return http.get(`/complexSearch?query=${searchTerm}&addRecipeInformation=true&apiKey=9d05ffae9fc648dc8ce6af2cdb0b69c6`);
    },

    getRandom() {
        return http.get('/random?apiKey=7673afa2c5ea4fe48c062eacd523f588');
    },

    getIngredients(id) {
        return http.get(`/${id}/ingredientWidget.json?apiKey=9d05ffae9fc648dc8ce6af2cdb0b69c6`);
    },

    // for recipe search details, gives us title, image and instructions
    getRecipeInfo(id) {
        return http.get(`https://api.spoonacular.com/recipes/${id}/information?includeNutrition=false&apiKey=9d05ffae9fc648dc8ce6af2cdb0b69c6`);
    }



}
import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {
    getUserName() {
        //  return axios.get(the username of the person logged in)
    },
    getMealHistory() {
        return http.get("/mealHistory");
    },
    getProfile() {
        return http.get("/profile");
    },
    getTrackBreakfast(userId, date) {
        return http.get("/logBreakfast/" + userId + "?" + date);
    },
    getTrackLunch(userId, date) {
        return http.get("/logLunch/" + userId + "?" + date);
    },
    getTrackDinner(userId, date) {
        return http.get("/logDinner/" + userId + "?" + date);
    },
    getTrackSnacks(userId, date) {
        return http.get("/logSnacks/" + userId + "?" + date);
    },
    addDinner(mealObject) {
        return axios.post("/addMeal", mealObject)
    },
    addBreakfast(mealObject) {
        return axios.post("/addMeal", mealObject)
    },
    addLunch(mealObject) {
        return axios.post("/addMeal", mealObject)
    },
    addSnack(mealObject) {
        return axios.post("/addMeal", mealObject)
    },
}
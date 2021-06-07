import axios from 'axios'

export default {
    // getLogBreakfast(id) {
    //     return axios.get("/logBreakfast/" + id)
    // },
    // getLogDinner(id) {
    //     return axios.get("/logDinner/" + id)
    // },
    // getLogLunch(id) {
    //     return axios.get("/logLunch/" + id);
    // },
    // getLogSnacks(id) {
    //     return axios.get("/logSnacks" + id)
    // },
    // getMealHistory(user_id) {
    //     return axios.get("/mealHistory/" + user_id)
    // },
    addFood(food){
        return axios.post("/addFood",food)
    },
    addMeal(mealObj){
        return axios.post("/addMeal",mealObj)
    },
    getMealId(id,typeOfMeal,date){
        return axios.get(`/getMealId/${id}/${typeOfMeal}?date=${date}`)
    },
    trackBreakfast(id,date){
        return axios.get(`/trackBreakfast/${id}?date=${date}`)
    },
    trackLunch(id,date){
        return axios.get(`/trackLunch/${id}?date=${date}`)
    },
    trackDinner(id,date){
        return axios.get(`/trackDinner/${id}?date=${date}`)
    },
    trackSnacks(id,date){
        return axios.get(`/trackSnacks/${id}?date=${date}`)
    }
}
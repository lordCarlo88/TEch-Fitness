import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {
    getViewBreakfast(userId, date) {
        return http.get(`/trackBreakfast/${userId}?date=${date}`)
    },
    getViewDinner(userId, date) {
        return http.get(`/trackDinner/${userId}?date=${date}`)
    },
    getViewLunch(userId, date) {
        return http.get(`/trackLunch/${userId}?date=${date}`);
    },
    getViewSnacks(userId, date) {
        return http.get(`/trackSnacks/${userId}?date=${date}`);
    }
}

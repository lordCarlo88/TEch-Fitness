import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {
    getProfilePage(id) {
        return http.get(`/profile/${id}`)
    },
    getEditLogin() {
        return http.get("/editLogin")
    },
    getProfilePicture() {
        return http.get("/profilePicture")
    },
    getHomePage() {
        return http.get("/home")
    },

    getProfile(profileId) {
        return axios.get(`/profile/${profileId}`);
    },
    editProfile(profile) {
        return axios.put("/editProfile", profile)
    },
    create(profile) {
        console.log(profile)
        return axios.post('/createProfile', profile);
    },
    findProfile(userId) {
        return axios.get('/findprofile/' + userId);
    }
}
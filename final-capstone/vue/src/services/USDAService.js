import axios from 'axios'


const http=axios.create({
    baseURL: "https://api.nal.usda.gov/fdc/v1"
})



export default{
    getAllFood(){
        return http.get('/foods/list?api_key=v7EDITL7Jgwu8GgsKmaqexDG1rQ3BdafBvBhSkmc')
    },
    searchFood(searchTerm){
        return http.get(`/foods/search?&query=${searchTerm}&api_key=v7EDITL7Jgwu8GgsKmaqexDG1rQ3BdafBvBhSkmc`)
    },
    searchFoodByfdcId(fdcId){
        return http.get(`/food/${fdcId}?api_key=v7EDITL7Jgwu8GgsKmaqexDG1rQ3BdafBvBhSkmc`)

    }
}
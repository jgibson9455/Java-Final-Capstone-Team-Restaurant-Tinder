import axios from 'axios';

const http = axios.create({
    baseURL: "https://api.yelp.com/v3/businesses"
});

const token = 'WU-YzESPMw0Dvkq6M3Kerv8Dr_DUUvO9MS2eKJ-qYDzl3J-bYgn2lnhyfp86br09UMH4pjS7deLLj4wxvXakHQsW3nqNmrYRs3DKZRtIwFZLFy4g77_8yv94wS3RX3Yx'


export default{
    getImage(name, location){
        return http.get(`/search?term=${name}&location=${location}`,{
            headers:{
                //"Accept": "application/json",
                "Authorization": `Bearer  ${token}`
            }
        })
    }
}
import axios from 'axios';

const http = axios.create({
    baseURL: "https://api.bing.microsoft.com/v7.0/images/"
})

//const keyOne = '70b5c8c8560f4156ad3114efc4bae479'
const keyTwo = '920efd6e79874b04a647e7a9b7b4156e'

export default{
    getImage(name){
        return http.get(`search?q=${name}&Subscription-Key=${keyTwo}`);
    }
}
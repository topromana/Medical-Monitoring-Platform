import axios from "axios";
import {HOST} from '../commons/hosts';

const endpoint = {
    signin: '/api/auth/signin',
    signup:'/api/auth/signup'
};
class AuthService {
    login(username, password) {
      return axios.post("http://localhost:8080/api/auth/signin", { username,password})
        .then(response => {
          if (response.data.token) {
            
            localStorage.setItem("user", JSON.stringify(response.data));
          }
          console.log("lalalaaaaaaaaaaa");
          return response.data;
        });
    }
    logout() {
        localStorage.removeItem("user");
      }
    
      register(user_name, user_password) {
          console.log(user_name +" "+ user_password);
        return axios.post("http://localhost:8080/api/auth/signup", {
          user_name,
          user_password
        });
      }
    
      getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
      }
    }
    
    export default new AuthService();
import axios from "axios";
import authHeader from './auth-header';

class CaregiverService{
    addCaregiver(user_name, user_gender, user_address, user_password,user_role) {
       
      return axios.post("http://localhost:8080/api/auth/signup", { user_name,user_gender, user_address, user_password,user_role}).then(response => {
          
        return response.data;
      });
    }
     updateCaregiver(user_id,user_name, user_birthdate,user_gender, user_address, medical_record,user_roles) {
       
        return axios.put("http://localhost:8080/api/users/"+user_id, { user_name,user_birthdate,user_gender, user_address,medical_record},{  headers: authHeader()}).then(response => {
            
          return response.data;
        });
      }
      getById(user_id){
        return axios.get("http://localhost:8080/api/users/"+user_id, {  headers: authHeader()}).then(response => {
            
          return response.data;
        });
      }
      getAllPatients(user_id){
        return axios.get("http://localhost:8080/api/patientsOfCaregiver/"+user_id, {  headers: authHeader()}).then(response => {
            
          return response.data;
        });

      }
}

export default new CaregiverService();
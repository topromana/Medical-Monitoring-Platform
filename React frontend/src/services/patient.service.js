import axios from "axios";
import authHeader from './auth-header';

class PatientService{
    addPatient(user_name, user_birthdate,user_gender, user_address, medical_record,user_password,user_role) {
       
      return axios.post("http://localhost:8080/api/auth/signup", { user_name,user_birthdate,user_gender, user_address,medical_record, user_password,user_role}).then(response => {
          
        return response.data;
      });
    }
    updatePatient(user_id,user_name, user_birthdate,user_gender, user_address, medical_record,user_roles) {
       
        return axios.put("http://localhost:8080/api/users/"+user_id, { "user_name":user_name,user_birthdate,user_gender, user_address,medical_record},{  headers: authHeader()}).then(response => {
            
          return response.data;
        });
      }
     assignCaregiver(user_id,c_user_id) {
      console.log("caregiverul inainte de  get" +c_user_id);
       var caregiver=axios.get("http://localhost:8080/api/users/"+c_user_id,{  headers: authHeader()}).then(response => {
        console.log(response.data);
        
        var caregiver_put = axios.put("http://localhost:8080/api/users/"+user_id, {"caregiver": {"user_id": response.data.user_id}},{  headers: authHeader()}).then(response => {
            
          return response.data;
        }); 

        return response.data;
      
      });
      console.log("dupa get"+caregiver);
        return caregiver;
      }
}

export default new PatientService();
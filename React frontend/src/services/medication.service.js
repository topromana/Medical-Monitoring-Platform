import axios from "axios";
import authHeader from './auth-header';
class MedicationService{
    addMedication( medication_name, medication_side_effects,medication_dosage) {      
        return axios.post('http://localhost:8080/api/medication',  {  medication_name, medication_side_effects,medication_dosage},{  headers: authHeader()}).then(response => {
          
              return response.data;
            });
      
        }
        updateMedication( medication_id,medication_name, medication_side_effects,medication_dosage) {      
          return axios.put('http://localhost:8080/api/medication/'+ medication_id,  {  medication_name, medication_side_effects,medication_dosage},{  headers: authHeader()}).then(response => {
            
                return response.data;
              });
        
          }
     getAllMedications(){
      return axios.get('http://localhost:8080/api/medication', {  headers: authHeader()}).then(response => {
          
        return response.data;
      });
     }   
      
}
export default new MedicationService();
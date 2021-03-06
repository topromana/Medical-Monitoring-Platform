import axios from "axios";
import authHeader from './auth-header';

class MedicationPlanService{
    addMedicationPlan( medication_list, intake_intervals,start_date,end_date,user) {      
        return axios.post('http://localhost:8080/api/medicationPlan',  { medication_list, intake_intervals,start_date,end_date,user},{  headers: authHeader()}).then(response => {
          
              return response.data;
            });
      
        }
    
      
}
export default new  MedicationPlanService();
import React, { Component } from "react";
import AuthService from "../services/authentication.service";
import CaregiverService from "../services/caregiver.service";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';    
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import axios from "axios";
import authHeader from '../services/auth-header';
export default class CaregiverHomepage extends Component {
  constructor(props) {
    super(props);
    this.state = { 
      patients : []
      
    };
    

  }
  componentDidMount(){
    var currentUser = AuthService.getCurrentUser();
    return axios.get("http://localhost:8080/api/patientsOfCaregiver/"+currentUser.user_id, {  headers: authHeader()}).then(response => {
            
      const patients = response.data;   
  
      this.setState({ patients });
        });

  }
  render() {
 
    
  
    return (
        <ul>
         { this.state.patients.map(patient=><li>
          <Card className="patient_card">
      <CardContent>
        <Typography variant="h5" component="h2">
        Name: {patient.user_name}
        </Typography>
        <Typography  color="textSecondary" gutterBottom >
           ID: {patient.user_id}
          </Typography>
        <Typography  color="textSecondary" gutterBottom>
        Address: {patient.user_address}
        </Typography>       
        <Typography  color="textSecondary">
         Gender: {patient.user_gender}
        </Typography>
        <Typography  color="textSecondary" gutterBottom>
          Birthdate: {patient.user_birthdate}
        </Typography>
        <Typography  color="textSecondary" gutterBottom>
          Medical record: {patient.medical_record}
        </Typography>
        
      </CardContent>
      
    </Card>
    </li>)}
        
    </ul>     
    );
  }
}
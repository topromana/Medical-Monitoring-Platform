import axios from "axios";
import { Component } from "react";
import authHeader from '../services/auth-header';

import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';    
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

export default class PatientsService extends Component{

  
       constructor(props){
           super(props);
        this.state = {
         patients:[]
        };
      
    }
    refreshPage() {
      window.location.reload(false);
    }
     
   deletePatient(user_id){
      axios.delete("http://localhost:8080/api/users/"+user_id,{  headers: authHeader()}).then(response => {
      this.refreshPage();
           })
           .catch(error => {
             console.log(error);
           });
   }
    componentDidMount(){
         axios.get("http://localhost:8080/api/patients",{  headers: authHeader()}).then(response => {
         const patients = response.data;   
  
         this.setState({ patients });
          })
          .catch(error => {
            console.log(error);
          });
    }
    render() {  
        
        return (
            
          <ul>
          {this.state.patients.map(patient=><li>
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
        <Typography  color="textSecondary" gutterBottom>
          Caregiver: {patient.caregiver_id}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick = {this.deletePatient.bind(this,patient.user_id)}>Delete patient</Button>
      </CardActions>
    </Card>
    </li>)}
          
      </ul>     
        );
      }
    
}


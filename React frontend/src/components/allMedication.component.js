import { Component } from "react";

import axios from "axios";
import authHeader from '../services/auth-header';
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';    
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';


export default class AllMedicationsComponent extends Component{

    constructor(props){
        super(props);
     this.state = {
      medications:[]
     };
   
 }
  refreshPage() {
    window.location.reload(false);
  }
   
 deleteMedication(medication_id){
    axios.delete("http://localhost:8080/api/medication/"+medication_id,{  headers: authHeader()}).then(response => {
    this.refreshPage();
         })
         .catch(error => {
           console.log(error);
         });
 }

 componentDidMount(){
      axios.get("http://localhost:8080/api/medication",{  headers: authHeader()}).then(response => {
      const medications = response.data;   
    
      this.setState({ medications });
       })
       .catch(error => {
         console.log(error);
       });
 }

   
 render() {  
     
     return (
         
        <ul>
            {this.state.medications.map(medication=><li>
            <Card className="medication_card">
        <CardContent>
          <Typography variant="h5" component="h2">
           Name: {medication.medication_name}
          </Typography>
          <Typography  color="textSecondary" gutterBottom >
           ID: {medication.medication_id}
          </Typography>
          <Typography  color="textSecondary" gutterBottom >
           Side effects: {medication.medication_side_effects}
          </Typography>
          <Typography  color="textSecondary">
           Dosage: {medication.medication_dosage}
          </Typography>
          
        </CardContent>
        <CardActions>
          <Button size="small" onClick={this.deleteMedication.bind(this,medication.medication_id)}>Delete medication</Button>
        </CardActions>
      </Card>
      </li>)}
            
        </ul>    
     );
   }

}   
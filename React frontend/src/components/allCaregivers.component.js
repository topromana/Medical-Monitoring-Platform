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

export default class CaregiversComponent extends Component{

  
       constructor(props){
           super(props);
        this.state = {
         caregivers:[]
        };
      
    }
    refreshPage() {
        window.location.reload(false);
      }
       
     deleteCaregiver(user_id){
        axios.delete("http://localhost:8080/api/users/"+user_id,{  headers: authHeader()}).then(response => {
        this.refreshPage();
             })
             .catch(error => {
               console.log(error);
             });
     }
    componentDidMount(){
         axios.get("http://localhost:8080/api/caregivers",{  headers: authHeader()}).then(response => {
         const caregivers = response.data;   
         this.setState({caregivers });
          })
          .catch(error => {
            console.log(error);
          });
    }
    render() {  
        
        return (
            
          <ul>
          {this.state.caregivers.map(caregiver=><li>
          <Card className="caregiver_card">
      <CardContent>
        <Typography variant="h5" component="h2">
         Name: {caregiver.user_name}
        </Typography>
        <Typography  color="textSecondary" gutterBottom >
           ID: {caregiver.user_id}
          </Typography>
        <Typography  color="textSecondary">
         Gender: {caregiver.user_gender}
        </Typography>
        <Typography  color="textSecondary">
         Address: {caregiver.user_address}
        </Typography>
        <Typography  color="textSecondary" gutterBottom>
          Birthdate: {caregiver.user_birthdate}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick = {this.deleteCaregiver.bind(this,caregiver.user_id)}>Delete caregiver</Button>
      </CardActions>
    </Card>
    </li>)}
          
      </ul>     
        );
      }
    
}


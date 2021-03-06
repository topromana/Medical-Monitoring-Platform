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
      activities:[]
     };
   
 }
  refreshPage() {
    window.location.reload(false);
  }
   

  
 componentDidMount(){
      axios.get("http://localhost:8080/api/activity",{  headers: authHeader()}).then(response => {
      const activities = response.data;   
    console.log(activities);
      this.setState({ activities });
       })
       .catch(error => {
         console.log(error);
       });
 }

   
 render() {  
     
     return (
         
        <ul>
            {this.state.activities.map(act=><li>
          
            <Card className="activity_card">
        <CardContent>
          <Typography variant="h5" component="h2">
           Type: {act.activity}
          </Typography>
          <Typography  color="textSecondary" gutterBottom >
           Patient ID: {act.user.user_id}
           
          </Typography>
          <Typography  color="textSecondary" gutterBottom >
          Start time: {act.start_time}
          </Typography>
          <Typography  color="textSecondary">
          End time: {act.end_time}
          </Typography>
          
        </CardContent>
        
      </Card>
      </li>)}
            
        </ul>    
     );
   }

}   
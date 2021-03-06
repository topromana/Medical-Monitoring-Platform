import React, { Component } from "react";
import AuthService from "../services/authentication.service";

export default class PatientHomepage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      currentUser: AuthService.getCurrentUser()
    };
  }

  render() {
    const { currentUser } = this.state;

    return (
      <div className="container">
        <header className="jumbotron">
          <h3>
            <strong>{currentUser.user_name}</strong> Profile
          </h3>
        </header>
        
        <p>
          <strong>Medical record:</strong>{" "}
          {currentUser.medical_record}
        </p>
      </div>
    );
  }
}
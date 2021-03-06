import React, { Component } from "react";
import AuthService from "../services/authentication.service";

export default class Profile extends Component {
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
          <strong>Token:</strong>{" "}
          {currentUser.token.substring(0, 20)} ...{" "}
          {currentUser.token.substr(currentUser.token.length - 20)}
        </p>
        <p>
          <strong>Id:</strong>{" "}
          {currentUser.user_id}
        </p>
        <strong>Authorities:</strong>
        <ul>
          {currentUser.roles &&
            currentUser.roles.map((role, index) =><li key={index}>{role}</li>)}
        </ul>
        <p>
          <strong>Gender:</strong>{" "}
          {currentUser.user_gender}
        </p>
         <p>
          <strong>Address:</strong>{" "}
          {currentUser.user_address}
        </p>
      </div>
    );
  }
}
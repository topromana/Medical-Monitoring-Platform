import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/authentication.service";

import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/homepage";
import Profile from "./components/profile.component";
import PatientHomepage from "./components/patientHomepage.component";
import CaregiverHomepage from "./components/caregiverHomepage.component";
import AddMedication from "./components/addMedication.component";
import AddMedicationPlan from "./components/addMedicationPlan.component";
import UpdateMedication from "./components/updateMedication.component";
import AddCaregiver from "./components/addCaregiver.component";
import AddPatient from "./components/addPatient.component";
import UpdatePatient from "./components/updatePatient.component";
import AssignCaregiver from "./components/assignCaregiver.component";
import UpdateCaregiver from "./components/updateCaregiver.component";
import AllMedications from "./components/allMedication.component";
import AllPatients from "./components/allPatients.component";
import AllCaregivers from "./components/allCaregivers.component";
import AddDoctor from "./components/addDoctor.component";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
      });
    }
  }

  logOut() {
    AuthService.logout();
  }

  render() {
    const { currentUser} = this.state;

    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            Medical Platform
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Home
              </Link>
            </li>           
          </div>

          {currentUser && currentUser.roles.includes("ROLE_PATIENT") && (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/patientHomepage"} className="nav-link">
                  Patient homepage
                </Link>
              </li>
              
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          
            
          )}
          {currentUser && currentUser.roles.includes("ROLE_CAREGIVER") && (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/patientsOfCaregiver"} className="nav-link">
                  All patients
                </Link>
              </li>
              
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          
            
          )}
          {currentUser && currentUser.roles.includes("ROLE_DOCTOR") ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/profile"} className="nav-link">
                  {currentUser.username}
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/allPatients"} className="nav-link">
                  All patients
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/addPatient"} className="nav-link">
                  Add Patient
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/updatePatient"} className="nav-link">
                 Update Patient
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/allCaregivers"} className="nav-link">
                  All caregivers
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/addCaregiver"} className="nav-link">
                  Add Caregiver
                </Link>
              </li>
               <li className="nav-item">
                <Link to={"/updateCaregiver"} className="nav-link">
                  Update Caregiver
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/addMedication"} className="nav-link">
                  Add Medication
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/allMedications"} className="nav-link">
                  All Medications
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/updateMedication"} className="nav-link">
                  Update Medication
                </Link>
              </li>
              
              <li className="nav-item">
                <Link to={"/assignCaregiver"} className="nav-link">
                  Assign Caregiver
                </Link>
              </li>
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>
            
              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>
            </div>
          )}
          
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/home"]} component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            <Route exact path="/patientHomepage" component={PatientHomepage} />
            <Route exact path="/patientsOfCaregiver" component={CaregiverHomepage} />
            <Route exact path="/allPatients" component={AllPatients} />
            <Route exact path="/addMedication" component={AddMedication} />
            <Route exact path="/updateMedication" component={UpdateMedication} />
            <Route exact path="/allMedications" component={AllMedications} />
            <Route exact path="/addCaregiver" component={AddCaregiver} />
            <Route exact path="/addDoctor" component={AddDoctor} />
            <Route exact path="/allCaregivers" component={AllCaregivers} />
            <Route exact path="/addPatient" component={AddPatient} />
            <Route exact path="/updatePatient" component={UpdatePatient} />
            <Route exact path="/updateCaregiver" component={UpdateCaregiver} />
            <Route exact path="/addMedicationPlan" component={AddMedicationPlan} />
            <Route exact path="/assignCaregiver" component={AssignCaregiver} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
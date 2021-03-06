import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import "../App.css";
import PatientService from "../services/patient.service";

const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

export default class AddPatient extends Component {
    constructor(props) {
      super(props);
      this.handleAddDoctor = this.handleAddDoctor.bind(this);
    this.onChangeUserName = this.onChangeUserName.bind(this);
    this.onChangeUserBirthdate = this.onChangeUserBirthdate.bind(this);
    this.onChangeUserGender = this.onChangeUserGender.bind(this);
    this.onChangeUserAddress = this.onChangeUserAddress.bind(this);
    this.onChangeUserMedicalRecord= this.onChangeUserMedicalRecord.bind(this);
    this.onChangeUserPassword = this.onChangeUserPassword.bind(this);
      this.state = {
        user_name: "",
        user_birthdate: "",
        user_gender: "",
        user_address: "",
        medical_record: "",
        user_password:"",
        user_role:["doctor"],
        loading: false,
        message: ""
      };
      
    }
    
    onChangeUserName(e) {
      this.setState({
        user_name: e.target.value
      });
    }
    onChangeUserBirthdate(e) {
        this.setState({
          user_birthdate: e.target.value
        });
      }
  
    onChangeUserGender(e) {
      this.setState({
        user_gender: e.target.value
      });
    }
    onChangeUserAddress(e) {
      this.setState({
        user_address: e.target.value
      });
    }
    onChangeUserMedicalRecord(e) {
        this.setState({
          medical_record: e.target.value
        });
      }
    onChangeUserPassword(e){
        this.setState({
            user_password: e.target.value
          });
    }
  
    handleAddDoctor(e) {
      e.preventDefault();
  
      this.setState({
        message: "",
        user_role:["doctor"],
        loading: true
      });
  
      this.form.validateAll();
      
      if (this.checkBtn.context._errors.length === 0) {
        PatientService.addPatient(this.state.user_name,this.state.user_birthdate, this.state.user_gender, this.state.user_address,
                                        this.state.medical_record,this.state.user_password,this.state.user_role).then(
          () => {
            //this.props.history.push("/allPatients");
            //window.location.reload();
          },
          error => {
            const resMessage =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
  
            this.setState({
              loading: false,
              message: resMessage
            });
          }
        );
      } else {
        this.setState({
          loading: false
        });
      }
    }
  
    render() {
      return (
        <div className="col-md-12">
          <div className="card card-container">
           
            <Form
              onSubmit={this.handleAddDoctor}
              ref={c => {
                this.form = c;
              }}
            >
              <div className="form-group">
                <label htmlFor="user_name">Patient Name</label>
                <Input
                  type="text"
                  className="form-control"
                  name="user_name"
                  placeholder = "user name"
                  value={this.state.user_name}
                  onChange={this.onChangeUserName}
                 
                />
              </div>
              <div className="form-group">
                <label htmlFor="user_birthdate">Birthdate</label>
                <Input
                  type="text"
                  className="form-control"
                  name="user_birthdate"
                  placeholder = "birthdate"
                  value={this.state.user_birthdate}
                  onChange={this.onChangeUserBirthdate}
                 
                />
              </div>
  
              <div className="form-group">
                <label htmlFor="user_gender">Gender</label>
                <Input
                  type="text"
                  className="form-control"
                  name="user_gender"
                  placeholder = "gender"
                  value={this.state.user_gender}
                  onChange={this.onChangeUserGender}
               
                />
              </div>
              <div className="form-group">
                <label htmlFor="user_address">Address</label>
                <Input
                  type="text"
                  className="form-control"
                  name="user_address"
                  placeholder = "address"
                  value={this.state.user_address}
                  onChange={this.onChangeUserAddress}
                 
                />
              </div>
              <div className="form-group">
                <label htmlFor="medical_record">Medical record</label>
                <Input
                  type="text"
                  className="form-control"
                  name="medical_record"
                  placeholder = "medical record"
                  value={this.state.medical_record}
                  onChange={this.onChangeUserMedicalRecord}
                  
                />
              </div>
              <div className="form-group">
                <label htmlFor="user_password">Password</label>
                <Input
                  type="password"
                  className="form-control"
                  name="user_password"
                  placeholder = "password"
                  value={this.state.user_password}
                  onChange={this.onChangeUserPassword}
                 
                />
              </div>
  
              <div className="form-group">
                <button
                  className="add-button"
                  disabled={this.state.loading}
                >
                  {this.state.loading && (
                    <span className="spinner-border spinner-border-sm"></span>
                  )}
                  <span>Add Patient</span>
                </button>
              </div>
  
              {this.state.message && (
                <div className="form-group">
                  <div className="alert alert-danger" role="alert">
                    {this.state.message}
                  </div>
                </div>
              )}
              <CheckButton
                style={{ display: "none" }}
                ref={c => {
                  this.checkBtn = c;
                }}
              />
            </Form>
          </div>
        </div>
      );
    }
  }
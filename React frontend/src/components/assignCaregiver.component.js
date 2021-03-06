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

export default class AssignCaregiver extends Component {
    constructor(props) {
      super(props);
      this.handleUpdatePatient = this.handleUpdatePatient.bind(this);
      this.onChangeUserId = this.onChangeUserId.bind(this);
    this.onChangeCaregiverId = this.onChangeCaregiverId.bind(this);
    
    
      this.state = {
          user_id:"",
        caregiver_id:"",
        user_role:["ROLE_PATIENT"],
        loading: false,
        message: ""
      };
      
    }
    onChangeUserId(e) {
        this.setState({
          user_id: e.target.value
        });
      }
    
    onChangeCaregiverId(e) {
      this.setState({
        caregiver_id:  e.target.value
      });
    }
    
  
    handleUpdatePatient(e) {
      e.preventDefault();
  
      this.setState({
        message: "",
        user_role:["ROLE_PATIENT"],
        loading: true
      });
  
      this.form.validateAll();

      console.log("HERE");
      
      if (this.checkBtn.context._errors.length === 0) {
        PatientService.assignCaregiver(this.state.user_id,this.state.caregiver_id).then(
          () => {
            this.props.history.push("/allPatients");
            window.location.reload();
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
              onSubmit={this.handleUpdatePatient}
              ref={c => {
                this.form = c;
              }}
            >
                 <div className="form-group">
                <label htmlFor="user_id">Patient Id</label>
                <Input
                  type="text"
                  className="form-control"
                  name="user_id"
                  placeholder = "user id"
                  value={this.state.user_id}
                  onChange={this.onChangeUserId}
                  validations={[required]}
                />
              </div>
              <div className="form-group">
                <label htmlFor="caregiver_id">Caregiver Id</label>
                <Input
                  type="text"
                  className="form-control"
                  name="caregiver_id"
                  placeholder = "caregiver id"
                  value={this.state.caregiver_id}
                  onChange={this.onChangeCaregiverId}
                  
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
                  <span>Assign Caregiver</span>
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
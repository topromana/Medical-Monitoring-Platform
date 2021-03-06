import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import "../App.css";
import MedicationService from "../services/medication.service";


const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

export default class UpdateMedication extends Component {
    constructor(props) {
      super(props);
      this.handleUpdate = this.handleUpdate.bind(this);
      this.onChangeMedicationId = this.onChangeMedicationId.bind(this);
    this.onChangeMedicationName = this.onChangeMedicationName.bind(this);
    this.onChangeMedicationSideEffects = this.onChangeMedicationSideEffects.bind(this);
    this.onChangeMedicationDosage = this.onChangeMedicationDosage.bind(this);
      this.state = {
          medication_id:"",
        medication_name: null,
        medication_side_effects: null,
        medication_dosage: null,
        loading: false,
        message: ""
      };
      
    }
    onChangeMedicationId(e) {
        this.setState({
          medication_id: e.target.value
        });
      }
    
    onChangeMedicationName(e) {
      this.setState({
        medication_name: e.target.value
      });
    }
  
    onChangeMedicationSideEffects(e) {
      this.setState({
        medication_side_effects: e.target.value
      });
    }
    onChangeMedicationDosage(e) {
      this.setState({
        medication_dosage: e.target.value
      });
    }
  
  
    handleUpdate(e) {
      e.preventDefault();
  
      this.setState({
        message: "",
        loading: true
      });
  
      this.form.validateAll();
      
      if (this.checkBtn.context._errors.length === 0) {
        MedicationService.updateMedication(this.state.medication_id,this.state.medication_name, this.state.medication_side_effects,
                        this.state.medication_dosage).then(
          () => {
            this.props.history.push("/allMedications");
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
              onSubmit={this.handleUpdate}
              ref={c => {
                this.form = c;
              }}
            >
                <div className="form-group">
                <label htmlFor="medication_id">Medication Id</label>
                <Input
                  type="text"
                  className="form-control"
                  name="medication_id"
                  placeholder = "medication id"
                  value={this.state.medication_id}
                  onChange={this.onChangeMedicationId}
                  validations={[required]}
                />
              </div>
  
              <div className="form-group">
                <label htmlFor="medication_name">Medication Name</label>
                <Input
                  type="text"
                  className="form-control"
                  name="medication_name"
                  placeholder = "medication name"
                  value={this.state.medication_name}
                  onChange={this.onChangeMedicationName}
                
                />
              </div>
  
              <div className="form-group">
                <label htmlFor="medication_side_effects">Side effects</label>
                <Input
                  type="text"
                  className="form-control"
                  name="medication_side_effects"
                  placeholder = "side effects"
                  value={this.state.medication_side_effects}
                  onChange={this.onChangeMedicationSideEffects}
                 
                />
              </div>
              <div className="form-group">
                <label htmlFor="medication_dosage">Dosage</label>
                <Input
                  type="text"
                  className="form-control"
                  name="medication_side_effects"
                  placeholder = "dosage"
                  value={this.state.medication_dosage}
                  onChange={this.onChangeMedicationDosage}
                 
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
                  <span>Update Medication</span>
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
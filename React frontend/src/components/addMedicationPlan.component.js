import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import "../App.css";
import MedicationPlanService from "../services/medicationPlan.service";


const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

export default class addMedicationPlan extends Component {
    constructor(props) {
      super(props);
      this.handleAdd = this.handleAdd.bind(this);
    this.onChangeMedicationList = this.onChangeMedicationList.bind(this);
    this.onChangeIntakeInterval= this.onChangeIntakeInterval.bind(this);
    this.onChangeStartDate = this.onChangeStartDate.bind(this);
    this.onChangeEndDate = this.onChangeEndDate.bind(this);
    this.onChangeUser = this.onChangeUser.bind(this);
      this.state = {
        medication_list: "",
        intake_intervals: "",
         start_date: "",
        end_date: "",
        user:"",
        loading: false,
        message: ""
      };
      
    }
    
    onChangeMedicationList(e) {
      this.setState({
        medication_list: e.target.value
      });
    }
  
    onChangeIntakeInterval(e) {
      this.setState({
        intake_intervals: e.target.value
      });
    }
    onChangeStartDate(e) {
      this.setState({
        start_date: e.target.value
      });
    }
    onChangeEndDate(e) {
        this.setState({
          end_date: e.target.value
        });
      }
      onChangeUser(e) {
        this.setState({
          user: e.target.value
        });
      }
  
  
    handleAdd(e) {
      e.preventDefault();
  
      this.setState({
        message: "",
        loading: true
      });
  
      this.form.validateAll();
      
      if (this.checkBtn.context._errors.length === 0) {
        MedicationPlanService.addMedicationPlan( this.state.medication_list, this.state.intake_intervals,this.state.start_date,
        this.state.end_date,this.state.user).then(
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
              onSubmit={this.handleAdd}
              ref={c => {
                this.form = c;
              }}
            >
              <div className="form-group">
                <label htmlFor="medication_list">Medication List</label>
                <Input
                  type="text"
                  className="form-control"
                  name="medication_list"
                  placeholder = "medication list"
                  value={this.state.medication_list}
                  onChange={this.onChangeMedicationList}
                  validations={[required]}
                />
              </div>
  
              <div className="form-group">
                <label htmlFor="intake_intervals">Intake intervals</label>
                <Input
                  type="text"
                  className="form-control"
                  name="intake_intervals"
                  placeholder = "intake intervals"
                  value={this.state.intake_intervals}
                  onChange={this.onChangeIntakeInterval}
                  validations={[required]}
                />
              </div>
              <div className="form-group">
                <label htmlFor="start_date">Start Date</label>
                <Input
                  type="text"
                  className="form-control"
                  name="start_date"
                  placeholder = "2020-02-03"
                  value={this.state.start_date}
                  onChange={this.onChangeStartDate}
                  validations={[required]}
                />
              </div>
              <div className="form-group">
                <label htmlFor="end_date">End Date</label>
                <Input
                  type="text"
                  className="form-control"
                  name="end_date"
                  placeholder = "2020-02-03"
                  value={this.state.end_date}
                  onChange={this.onChangeEndDate}
                  validations={[required]}
                />
              </div>
              <div className="form-group">
                <label htmlFor="user">User</label>
                <Input
                  type="text"
                  className="form-control"
                  name="user"
                  placeholder = "user id"
                  value={this.state.user}
                  onChange={this.onChangeUser}
                  validations={[required]}
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
                  <span>Add Medication Plan</span>
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
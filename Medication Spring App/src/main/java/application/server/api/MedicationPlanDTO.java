package application.server.api;

import application.entities.Medication;
import application.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

public class MedicationPlanDTO implements Serializable {
    private Long medicationplan_id;
    List<String> medication_names_list = new ArrayList<String>();
    List<String> medication_dosage_list = new ArrayList<String>();
    private String intake_intervals;
    private Date start_date;
    private Date end_date;

    public MedicationPlanDTO() {

    }

    @Override public String toString() {
        return format("Acquire succesful with start date '%s'.", start_date);
    }

    public MedicationPlanDTO(Long medicationplan_id, List<String> medication_names_list,
                             List<String> medication_dosage_list, String intake_intervals, Date start_date, Date end_date) {
        this.medicationplan_id = medicationplan_id;
        this.medication_names_list = medication_names_list;
        this.medication_dosage_list = medication_dosage_list;
        this.intake_intervals = intake_intervals;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public void setMedicationplan_id(Long medicationplan_id) {
        this.medicationplan_id = medicationplan_id;
    }

    public void setMedication_names_list(List<String> medication_names_list) {
        this.medication_names_list = medication_names_list;
    }

    public void setMedication_dosage_list(List<String> medication_dosage_list) {
        this.medication_dosage_list = medication_dosage_list;
    }

    public void setIntake_intervals(String intake_intervals) {
        this.intake_intervals = intake_intervals;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    public Long getMedicationplan_id() {
        return medicationplan_id;
    }

    public List<String> getMedication_names_list() {
        return medication_names_list;
    }

    public List<String> getMedication_dosage_list() {
        return medication_dosage_list;
    }

    public String getIntake_intervals() {
        return intake_intervals;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }
}
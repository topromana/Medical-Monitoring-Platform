package application.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medication_plan")
public class MedicationPlan {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "medicationplan_id")
    private Long medicationplan_id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "medication_list",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_plan_id"))
    List<Medication> medication_list = new ArrayList<Medication>();
    @Column(name = "intake_intervals")
    private String intake_intervals;

    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "end_date")
    private Date end_date;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private User user;

    public Long getMedicationplan_id() {
        return medicationplan_id;
    }

    public void setMedicationplan_id(Long medicationplan_id) {
        this.medicationplan_id = medicationplan_id;
    }

    public List<Medication> getMedication_list() {
        return medication_list;
    }

    public void setMedication_list(List<Medication> medication_list) {
        this.medication_list = medication_list;
    }

    public String getIntake_intervals() {
        return intake_intervals;
    }

    public void setIntake_intervals(String intake_intervals) {
        this.intake_intervals = intake_intervals;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package application.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "medication_id")
    private Long medication_id;
    @Column(name = "medication_name")
    private String medication_name;
    @Column(name = "medication_side_effects")
    private String medication_side_effects;
    @Column(name = "medication_dosage")
    private String medication_dosage;

    public Long getMedication_id() {
        return medication_id;
    }

    public void setMedication_id(Long medication_id) {
        this.medication_id = medication_id;
    }

    public String getMedication_name() {
        return medication_name;
    }

    public void setMedication_name(String medication_name) {
        this.medication_name = medication_name;
    }

    public String getMedication_side_effects() {
        return medication_side_effects;
    }

    public void setMedication_side_effects(String medication_side_effects) {
        this.medication_side_effects = medication_side_effects;
    }

    public String getMedication_dosage() {
        return medication_dosage;
    }

    public void setMedication_dosage(String medication_dosage) {
        this.medication_dosage = medication_dosage;
    }
}

package application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "name")
    private String name;
    @Column(name = "user_birthdate")
    private Date user_birthdate;
    @Column(name = "user_gender")
    private String user_gender;
    @Column(name = "user_address")
    private String user_address;
    @Column(name= "medical_record")
    private String medical_record;
    @NotBlank
    @Size(max = 120)
    private String user_password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name="caregiver_id")
    @JsonBackReference
    private User caregiver;

    @OneToMany(mappedBy="caregiver",fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<User> patients_list = new HashSet<User>();

    public User( String user_name, Date user_birthdate, String user_gender, String user_address, String medical_record,
                String user_password) {
        this.name=user_name;
        this.user_birthdate = user_birthdate;
        this.user_gender = user_gender;
        this.user_address = user_address;
        this.medical_record = medical_record;
        this.user_password = user_password;
    }

    public User() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return name;
    }

    public void setUser_name(String user_name) {
        this.name = user_name;
    }

    public Date getUser_birthdate() {
        return user_birthdate;
    }

    public void setUser_birthdate(Date user_birthdate) {
        this.user_birthdate = user_birthdate;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public User getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(User caregiver) {
        this.caregiver = caregiver;
    }

    public Set<User> getPatients_list() {
        return patients_list;
    }

    public void setPatients_list(Set<User> patients_list) {
        this.patients_list = patients_list;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String password) {
        this.user_password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

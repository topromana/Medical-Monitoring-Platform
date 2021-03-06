package application.payloads.requestObj;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
    @NotBlank
    private Date user_birthdate;
    @NotBlank
    private String user_gender;
    @NotBlank
    private String user_address;
    @NotBlank
    private String medical_record;
    @NotBlank
    @Size(max = 50)
    private String user_password;

    private Set<String> user_role;

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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Set<String> getUser_role() {
        return user_role;
    }

    public void setUser_role(Set<String> user_role) {
        this.user_role = user_role;
    }
}

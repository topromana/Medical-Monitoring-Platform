package application.payloads.responseObj;

import java.util.Date;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long user_id;
    private String name;
    private Date user_birthdate;
    private String user_gender;
    private String user_address;
    private String medical_record;
    private List<String> roles;

    public JwtResponse(String token, Long user_id, String user_name, Date user_birthdate, String user_gender,
                       String user_address, String medical_record, List<String> roles) {
        this.token = token;
        this.user_id = user_id;
        this.name = user_name;
        this.user_birthdate = user_birthdate;
        this.user_gender = user_gender;
        this.user_address = user_address;
        this.medical_record = medical_record;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String type) {
        this.type = type;
    }
}

package application.payloads.requestObj;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String user_password;

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String password) {
        this.user_password = password;
    }
}

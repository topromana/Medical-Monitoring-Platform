package application.security.servicesSecurity;

import application.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImplementation implements UserDetails {

    private Long user_id;
    private String name;
    private Date user_birthdate;
    private String user_gender;
    private String user_address;
    private String medical_record;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplementation(Long user_id, String user_name, Date user_birthdate, String user_gender,
                                     String user_address, String medical_record, String password,
                                     Collection<? extends GrantedAuthority> authorities) {
        this.user_id = user_id;
        this.name = user_name;
        this.user_birthdate = user_birthdate;
        this.user_gender = user_gender;
        this.user_address = user_address;
        this.medical_record = medical_record;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImplementation build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole_name().name()))
                .collect(Collectors.toList());
        return new UserDetailsImplementation(
                user.getUser_id(),
                user.getUser_name(),
                user.getUser_birthdate(),
                user.getUser_gender(),
                user.getUser_address(),
                user.getMedical_record(),
                user.getPassword(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return name;
    }

    public Date getUser_birthdate() {
        return user_birthdate;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public String getUser_address() {
        return user_address;
    }

    public String getMedical_record() {
        return medical_record;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImplementation user = (UserDetailsImplementation) o;
        return Objects.equals(user_id, user.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, name, user_birthdate, user_gender, user_address, medical_record, password, authorities);
    }
}

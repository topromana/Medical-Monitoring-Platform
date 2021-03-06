package application.entities;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles rolename;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public ERoles getRole_name() {
        return rolename;
    }

    public void setRole_name(ERoles role_name) {
        this.rolename = role_name;
    }
}

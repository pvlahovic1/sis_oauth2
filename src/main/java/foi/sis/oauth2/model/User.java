package foi.sis.oauth2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "sis_projekt")
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}

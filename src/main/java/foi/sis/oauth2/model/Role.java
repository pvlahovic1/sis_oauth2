package foi.sis.oauth2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "sis_projekt")
@Data
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue
    private Integer roleId;

    @Column(name = "name")
    private String name;

}

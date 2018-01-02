package foi.sis.oauth2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "scope", schema = "sis_projekt")
@Data
public class Scope {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

}

package foi.sis.oauth2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "resource_type", schema = "sis_projekt")
@Data
public class ResourceType {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;
}

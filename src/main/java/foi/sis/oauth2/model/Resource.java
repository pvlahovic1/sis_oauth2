package foi.sis.oauth2.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource", schema = "sis_projekt")
@Data
public class Resource {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "data")
    private String data;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name="id_user", referencedColumnName="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="id_resource_type", referencedColumnName="id")
    private ResourceType resourceType;

}

package foi.sis.oauth2.repository;

import foi.sis.oauth2.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByClientUniqueDescript(String clientUniqueDescript);

}

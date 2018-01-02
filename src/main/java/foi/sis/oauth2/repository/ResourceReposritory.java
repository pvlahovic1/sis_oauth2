package foi.sis.oauth2.repository;

import foi.sis.oauth2.model.Resource;
import foi.sis.oauth2.model.ResourceType;
import foi.sis.oauth2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ResourceReposritory extends JpaRepository<Resource, Integer> {

    List<Resource> findAllByUserAndResourceTypeIn(User users, Collection<ResourceType> resourceTypes);

}

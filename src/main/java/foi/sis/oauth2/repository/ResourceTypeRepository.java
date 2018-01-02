package foi.sis.oauth2.repository;

import foi.sis.oauth2.model.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceTypeRepository extends JpaRepository<ResourceType, Integer> {

    ResourceType getByName(String name);

}

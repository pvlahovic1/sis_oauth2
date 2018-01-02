package foi.sis.oauth2.service;

import foi.sis.oauth2.exception.UserCredentialsException;
import foi.sis.oauth2.model.Client;
import foi.sis.oauth2.model.Resource;
import foi.sis.oauth2.model.ResourceType;
import foi.sis.oauth2.model.User;
import foi.sis.oauth2.model.wrapper.UserPrivateData;
import foi.sis.oauth2.repository.ClientRepository;
import foi.sis.oauth2.repository.ResourceReposritory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceReposritory resourceReposritory;
    private final ClientRepository clientRepository;

    @Override
    public List<UserPrivateData> fetchAllUserPrivateData(User user, String clientId) {
        List<UserPrivateData> userPrivateDataList = new ArrayList<>();

        Client client = clientRepository.findByClientUniqueDescript(clientId);

        List<Resource> resources = resourceReposritory.findAllByUserAndResourceTypeIn(user, client.getResourceTypes());

        for (Resource resource : resources) {
            UserPrivateData userPrivateData = new UserPrivateData();
            userPrivateData.setId(resource.getId());
            userPrivateData.setData(resource.getData());
            userPrivateData.setCreationDate(resource.getCreationDate());
            userPrivateData.setResourceType(resource.getResourceType().getName());
            userPrivateData.setUser(resource.getUser().getName() + " " + resource.getUser().getSurname());

            userPrivateDataList.add(userPrivateData);
        }

        return userPrivateDataList;
    }

    @Override
    public void saveNewResource(String data, User user, ResourceType resourceType) {
        Resource resource = new Resource();

        resource.setData(data);
        resource.setCreationDate(LocalDateTime.now());
        resource.setResourceType(resourceType);
        resource.setUser(user);

        resourceReposritory.save(resource);
    }

    @Override
    public void deleteResource(String clientId, User user, Integer id) {
        Client client = clientRepository.findByClientUniqueDescript(clientId);

        Resource resource = resourceReposritory.findOne(id);

        if (resource != null) {
            if (resource.getUser().getUserId() == user.getUserId()) {
                if (client.getResourceTypes().contains(resource.getResourceType())) {
                    resourceReposritory.delete(resource);
                } else {
                    throw new UserCredentialsException("This client canot fetch this resource");
                }
            } else {
                throw new UserCredentialsException("This user is not owner of that resource");
            }
        } else {
            throw new UserCredentialsException("That resource doesnt exists");
        }
    }
}

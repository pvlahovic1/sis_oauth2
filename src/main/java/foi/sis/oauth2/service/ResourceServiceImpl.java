package foi.sis.oauth2.service;

import foi.sis.oauth2.model.Client;
import foi.sis.oauth2.model.Resource;
import foi.sis.oauth2.model.User;
import foi.sis.oauth2.model.wrapper.UserPrivateData;
import foi.sis.oauth2.repository.ClientRepository;
import foi.sis.oauth2.repository.ResourceReposritory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

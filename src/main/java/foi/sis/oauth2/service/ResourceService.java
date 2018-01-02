package foi.sis.oauth2.service;


import foi.sis.oauth2.model.User;
import foi.sis.oauth2.model.wrapper.UserPrivateData;

import java.util.List;

public interface ResourceService {

    List<UserPrivateData> fetchAllUserPrivateData(User user, String clientId);

}

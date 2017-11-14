package foi.sis.oauth2.service;

import foi.sis.oauth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

@RequiredArgsConstructor
public class SisClientDetailService implements ClientDetailsService {

    private final UserRepository userRepository;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null; //return userRepository.findByUsername(s);
    }
}

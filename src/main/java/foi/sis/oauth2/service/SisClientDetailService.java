package foi.sis.oauth2.service;

import foi.sis.oauth2.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SisClientDetailService implements ClientDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return clientRepository.findByClientUniqueDescript(s);
    }
}

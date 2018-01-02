package foi.sis.oauth2.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SisGrantedAuthority implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}

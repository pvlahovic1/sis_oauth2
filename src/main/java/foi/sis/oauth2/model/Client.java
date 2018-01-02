package foi.sis.oauth2.model;

import foi.sis.oauth2.config.SisGrantedAuthority;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "client", schema = "sis_projekt")
@Data
public class Client implements ClientDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "client_unique_descript")
    private String clientUniqueDescript;

    @Column(name = "token_validity")
    private Integer tokenValidity;

    @Column(name = "secret")
    private String secret;

    @Column(name = "secret_required")
    private boolean secretRequired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_resource_type",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_type_id"))
    private Set<ResourceType> resourceTypes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_scope",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "scope_id"))
    private Set<Scope> scopes = new HashSet<>();

    @Override
    public String getClientId() {
        return this.clientUniqueDescript;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceTypes.stream().map(ResourceType::getName).collect(Collectors.toSet());
    }

    @Override
    public String getClientSecret() {
        return this.secret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return this.scopes.stream().map(Scope::getName).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> authorizedGrantTypes = new HashSet<>();
        authorizedGrantTypes.add("client_credentails");
        authorizedGrantTypes.add("password");

        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SisGrantedAuthority("ROLE_CLIENT"), new SisGrantedAuthority("ROLE_TRUSTED_CLIENT"));
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.tokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.tokenValidity;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}

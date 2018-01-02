package foi.sis.oauth2.controller;

import foi.sis.oauth2.exception.UserCredentialsException;
import foi.sis.oauth2.model.User;
import foi.sis.oauth2.model.wrapper.UserPrivateData;
import foi.sis.oauth2.repository.ResourceTypeRepository;
import foi.sis.oauth2.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-api/data")
public class DataController {

    private final ResourceService resourceService;
    private final ResourceTypeRepository resourceTypeRepository;

    @GetMapping
    public List<UserPrivateData> getUserPrivateData(HttpServletResponse httpServletResponse) throws IOException {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        if (!canClientRead()) {
            httpServletResponse.sendError(400, "This client cannot read data");
            return null;
        }

        User user = (User) oAuth2Authentication.getPrincipal();
        return resourceService.fetchAllUserPrivateData(user, oAuth2Authentication.getOAuth2Request().getClientId());
    }

    @PostMapping("/new")
    public void addnewBiljeska(HttpServletResponse httpServletResponse, @RequestBody Map<String, String> body) throws IOException {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        if (!canClientWrite()) {
            httpServletResponse.sendError(400, "This client cannot write data");
            return;
        }

        User user = (User) oAuth2Authentication.getPrincipal();
        resourceService.saveNewResource(body.get("data"), user, resourceTypeRepository.getByName(body.get("resourceType")));
    }

    @PostMapping("/delete")
    public void deleteResource(HttpServletResponse httpServletResponse, @RequestBody Map<String, Integer> body) throws IOException {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        if (!canClientDelete()) {
            httpServletResponse.sendError(400, "This client cannot delete data");
            return;
        }

        User user = (User) oAuth2Authentication.getPrincipal();
        String clientId = oAuth2Authentication.getOAuth2Request().getClientId();

        try {
            resourceService.deleteResource(clientId, user, body.get("id"));
        } catch (UserCredentialsException e) {
            httpServletResponse.sendError(400, e.getMessage());
        }
    }

    private boolean canClientRead() {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        return oAuth2Authentication.getOAuth2Request().getScope().contains("truest")
                    || oAuth2Authentication.getOAuth2Request().getScope().contains("read");
    }

    private boolean canClientWrite() {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        return oAuth2Authentication.getOAuth2Request().getScope().contains("truest")
                || oAuth2Authentication.getOAuth2Request().getScope().contains("write");
    }

    private boolean canClientDelete() {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        return oAuth2Authentication.getOAuth2Request().getScope().contains("truest")
                || oAuth2Authentication.getOAuth2Request().getScope().contains("delete");
    }


}

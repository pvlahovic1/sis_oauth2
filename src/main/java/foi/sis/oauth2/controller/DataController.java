package foi.sis.oauth2.controller;

import foi.sis.oauth2.model.User;
import foi.sis.oauth2.model.wrapper.UserPrivateData;
import foi.sis.oauth2.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-api/data")
public class DataController {

    private final ResourceService resourceService;

    @GetMapping
    public List<UserPrivateData> getUserPrivateData(HttpServletResponse httpServletResponse) throws IOException {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        if (!oAuth2Authentication.getOAuth2Request().getScope().contains("read")) {
            httpServletResponse.sendError(400, "This client cannot read data");
            return null;
        }

        User user = (User) oAuth2Authentication.getPrincipal();
        return resourceService.fetchAllUserPrivateData(user, oAuth2Authentication.getOAuth2Request().getClientId());
    }


}

package foi.sis.oauth2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-api")
public class MainController {

    @GetMapping("/public")
    public String getPublic() {
        return "Public";
    }

    @GetMapping("/private")
    public String getPrivate() {
        return "Private";
    }


}

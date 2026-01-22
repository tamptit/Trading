package storm.server.gateway.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {


    @GetMapping(value = "/token")
    public String getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        return authorizedClient.getAccessToken().getTokenValue();
    }

    @PostMapping("/get")
    public ResponseEntity<String> getToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid Authorization header");
        }
    }
    //
    @GetMapping(value = "/public")
    public String homePage() {
        return ("Storm hello world");
    }


    @GetMapping(value = "/user/welcome")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getWelcome() {
        return "Welcome Userrrrrr";
    }

    @GetMapping(value = "/admin/welcome")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //TODO: practices hasAuthority
    public String getListCustomers() {
        return "Welcome Adminnnnnn";
    }


}

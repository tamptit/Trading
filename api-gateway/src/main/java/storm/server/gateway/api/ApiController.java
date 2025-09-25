package storm.server.gateway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import storm.server.gateway.model.Users;
import storm.server.gateway.service.UserServiceImpl;
import storm.server.gateway.service.UsersService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UsersService userService;

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

    @GetMapping("/public/all")
    public ResponseEntity<List<Users>> getAll(){
        List<Users> usersList = userService.getAllUsers();
        return new ResponseEntity<List<Users>>(usersList, HttpStatus.OK);
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

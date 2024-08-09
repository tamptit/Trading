package storm.server.gateway;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.*;
import com.nimbusds.jwt.proc.JWTProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Logger;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        System.out.println(" -----------------       ApiGateway starting...");
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        JwtDecoder jwtDecoder = new NimbusJwtDecoder(new JWTProcessor<SecurityContext>() {
            @Override
            public JWTClaimsSet process(String s, SecurityContext securityContext) throws ParseException, BadJOSEException, JOSEException {
                return null;
            }

            @Override
            public JWTClaimsSet process(JWT jwt, SecurityContext securityContext) throws BadJOSEException, JOSEException {
                return null;
            }

            @Override
            public JWTClaimsSet process(PlainJWT plainJWT, SecurityContext securityContext) throws BadJOSEException, JOSEException {
                return null;
            }

            @Override
            public JWTClaimsSet process(SignedJWT signedJWT, SecurityContext securityContext) throws BadJOSEException, JOSEException {
                return null;
            }

            @Override
            public JWTClaimsSet process(EncryptedJWT encryptedJWT, SecurityContext securityContext) throws BadJOSEException, JOSEException {
                return null;
            }
        });
        return jwtDecoder;
    }


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
//    @GetMapping(value = "/permitAll")
//    public Mono<String> testPermitAll() {
//        return Mono.just("Test Permit All");
//    }
//
//    @GetMapping(value = "/customers")
//    public Mono<String> getCustomers() {
//        return Mono.just("Authenticated Customer");
//    }

    @GetMapping(path = "/")
    public HashMap index() {
        // get a successful user login
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new HashMap(){{
            put("hello", user.getAttribute("name"));
            put("your email is", user.getAttribute("email"));
        }};
    }


}

package storm.server.gateway.config;

public class StringProperties {

    public static final String GROUPS = "groups";
    public static final String REALM_ACCESS_CLAIM = "realm_access";
    public static final String ROLES_CLAIM = "roles";
    public static final String[] AUTH_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/regis",
            "/api/public/**",
            "/api/regis/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/swagger-ui/**",
    };
}

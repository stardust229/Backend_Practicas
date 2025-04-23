package com.product.api.auth;

import java.util.HashMap;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UsernameOrEmailAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 8008995706968351846L;
	private final Object principal;
    private final String credentials;
    
    public UsernameOrEmailAuthenticationToken(HashMap<String, String> principal, String credentials) {
        super(null);
        this.principal = principal.get("username") != null ? principal.get("username") : principal.get("correo");
        this.credentials = credentials;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
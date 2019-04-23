package com.vinodshivaram.practice.springboot.service;

import com.vinodshivaram.practice.springboot.exceptions.UnAuthorizedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenService implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object token = authentication.getPrincipal();
        if (token == null || token.toString().isEmpty())
            throw new UnAuthorizedException("No Authorization header was present in the request");

        authentication.setAuthenticated(validateToken((String) token));
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(PreAuthenticatedAuthenticationToken.class);
    }

    private boolean validateToken(String token) {
        if (token.equalsIgnoreCase("abcdef"))
            return true;
        return false;
    }
}

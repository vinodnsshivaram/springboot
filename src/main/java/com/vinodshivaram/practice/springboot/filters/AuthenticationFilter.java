package com.vinodshivaram.practice.springboot.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodshivaram.practice.springboot.exceptions.ErrorResponse;
import com.vinodshivaram.practice.springboot.exceptions.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    protected Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    protected ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing Authentication Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<String> token = Optional.ofNullable(request.getHeader("Authorization"));

        try {
            processAuthorizationToken(request, response, token);
        } catch (UnAuthorizedException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, request.getRequestURI(), request.getMethod(), e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("content-type", "application/json");
            response.getWriter().write(mapper.writeValueAsString(errorResponse));
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void processAuthorizationToken(HttpServletRequest request, HttpServletResponse response, Optional<String> token) {
        if (!token.isPresent()) {
            throw new UnAuthorizedException("Authorization Header was not present in the request");
        } else {
            validateAuthorizationToken(token);
        }
    }

    private void validateAuthorizationToken(Optional<String> token) {

    }

    @Override
    public void destroy() {

    }
}

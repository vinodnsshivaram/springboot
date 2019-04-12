package com.vinodshivaram.practice.springboot.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Spring Web filter for logging request and response.
 *
 * @see org.springframework.web.filter.AbstractRequestLoggingFilter
 * @see ContentCachingRequestWrapper
 * @see ContentCachingResponseWrapper
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestAndResponseLoggingFilter implements Filter {
    protected static Logger logger = LoggerFactory.getLogger(RequestAndResponseLoggingFilter.class);
    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
            MediaType.valueOf("text/*"),
            MediaType.APPLICATION_FORM_URLENCODED,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.valueOf("application/*+json"),
            MediaType.valueOf("application/*+xml"),
            MediaType.MULTIPART_FORM_DATA
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing Request and Response Logging Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
    }

    @Override
    public void destroy() {

    }

    protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } finally {
            logRequestResponse(request, response);
            response.copyBodyToResponse();
        }
    }

    protected void logRequestResponse(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        if (logger.isInfoEnabled()) {
            logRequest(request);
            logResponse(response);
        }
    }

    private static void logRequest(ContentCachingRequestWrapper request) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            logger.info("{} {}", request.getMethod(), request.getRequestURI());
        } else {
            logger.info("{} {}?{}", request.getMethod(), request.getRequestURI(), queryString);
        }
        Collections.list(request.getHeaderNames()).forEach(headerName ->
                Collections.list(request.getHeaders(headerName)).forEach(headerValue ->
                        logger.info("{}: {}", headerName, headerValue)));

        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, request.getContentType(), request.getCharacterEncoding());
        }
    }

    private static void logResponse(ContentCachingResponseWrapper response) {
        int status = response.getStatus();
        logger.info("{} {}", status, HttpStatus.valueOf(status).getReasonPhrase());
        response.getHeaderNames().forEach(headerName ->
                response.getHeaders(headerName).forEach(headerValue ->
                        logger.info("{}: {}", headerName, headerValue)));

        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, response.getContentType(), response.getCharacterEncoding());
        }
    }

    private static void logContent(byte[] content, String contentType, String contentEncoding) {
        MediaType mediaType = MediaType.valueOf(contentType);
        boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            try {
                String contentString = new String(content, contentEncoding);
                contentString = contentString.replace("\n", "").replace("\t", "");
                Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> logger.info("{}", line));
            } catch (UnsupportedEncodingException e) {
                logger.info("[{} bytes content]", content.length);
            }
        } else {
            logger.info("[{} bytes content]", content.length);
        }
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }
}
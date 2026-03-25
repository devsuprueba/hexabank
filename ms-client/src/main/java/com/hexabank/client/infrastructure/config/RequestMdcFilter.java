package com.hexabank.client.infrastructure.config;

import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Servlet filter that populates MDC with request identifiers (traceId, requestId).
 * Reads headers `X-Trace-Id` and `X-Request-Id` if present; otherwise generates UUIDs.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestMdcFilter implements Filter {

    public static final String TRACE_ID = "traceId";
    public static final String REQUEST_ID = "requestId";
    public static final String HEADER_TRACE_ID = "X-Trace-Id";
    public static final String HEADER_REQUEST_ID = "X-Request-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String traceId = req.getHeader(HEADER_TRACE_ID);
        String requestId = req.getHeader(HEADER_REQUEST_ID);
        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString();
        }
        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }
        MDC.put(TRACE_ID, traceId);
        MDC.put(REQUEST_ID, requestId);
        // echo back identifiers in response headers
        res.setHeader(HEADER_TRACE_ID, traceId);
        res.setHeader(HEADER_REQUEST_ID, requestId);
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID);
            MDC.remove(REQUEST_ID);
        }
    }

}

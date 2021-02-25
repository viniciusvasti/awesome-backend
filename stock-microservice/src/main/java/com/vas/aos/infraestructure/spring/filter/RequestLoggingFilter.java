package com.vas.aos.infraestructure.spring.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final var httpServletRequest = (HttpServletRequest) servletRequest;
        String currentCorrId = httpServletRequest.getHeader("CID");

        if (currentCorrId == null) {
            currentCorrId = UUID.randomUUID().toString();
        }
        MDC.put("CID", currentCorrId);

        try {
            filterChain.doFilter(httpServletRequest, servletResponse);
        } finally {
            MDC.remove("CID");
        }
    }

    @Override
    public void destroy() {
    }

}
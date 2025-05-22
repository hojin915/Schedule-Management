package com.example.schedulemanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {
            "GET /schedules",
            "GET /schedules/*",
            "POST /users/signup",
            "GET /users/login",
            "POST /users/login"
    };

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String method = httpRequest.getMethod();
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse =  (HttpServletResponse) servletResponse;

        log.info("Login Filter");

        if(!isWhiteList(method, requestURI)){
            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute("sessionKey") == null){
                throw new RuntimeException("login required");
            }

            log.info("longin success");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String method, String requestURI){
        String request = method + " " + requestURI;
        return PatternMatchUtils.simpleMatch(WHITE_LIST, request);
    }
}

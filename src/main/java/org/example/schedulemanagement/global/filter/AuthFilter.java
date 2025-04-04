package org.example.schedulemanagement.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.global.AuthConst;
import org.example.schedulemanagement.dto.errordto.ErrorResponseDto;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.example.schedulemanagement.global.exception.MyException;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {
    private static final String[] WHITE_LIST = {"/auth/sign-up", "/auth/login",  "/swagger-ui/*","/v3/api-docs/*", "/v3/api-docs", "/test"};

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (!isWhiteList(requestURI)) {
            log.info("method : {}   Url{}", httpRequest.getMethod(), httpRequest.getRequestURI());

            try {
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(AuthConst.LOGIN_USER) == null) {
                    throw new CustomeException(ErrorCode.UNAUTHORIZED);
                }
            } catch (MyException e) {
                sendErrorResponse(httpResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
    private void sendErrorResponse(HttpServletResponse response, MyException ex) throws IOException {
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(ex.getHttpStatus().value());

        ErrorResponseDto errorResponse = new ErrorResponseDto(ex);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(jsonResponse);
        response.getWriter().flush();

    }
}



package org.example.schedulemanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.config.AuthConst;
import org.example.schedulemanagement.exception.UnAuthorizedException;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {
    private static final String[] WHITE_LIST = {"/auth/sign-up", "/auth/login"};
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if(!isWhiteList(requestURI)){
            HttpSession session = httpRequest.getSession(false);
            if(session == null || session.getAttribute(AuthConst.LOGIN_USER) == null){
                throw new UnAuthorizedException(); //에러처리 조금 더 고민해보기
            }
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}



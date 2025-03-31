package org.example.schedulemanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.config.Const;
import org.example.schedulemanagement.dto.authdto.SavedSessionDto;
import org.example.schedulemanagement.exception.AuthenticationException;
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
            if(session == null || session.getAttribute(Const.LOGIN_USER) == null){
                throw new AuthenticationException(); //왜 controllerAdvice에서 처리못할/까?
            }
        }

        //강의와 다른이유? 강의는 chain이었음
        filterChain.doFilter(httpRequest, httpResponse);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

    public static Long getUserId(HttpServletRequest request){
        SavedSessionDto savedSessionDto = (SavedSessionDto)request.getSession().getAttribute(Const.LOGIN_USER);
        return savedSessionDto.getUserId();
    }
}

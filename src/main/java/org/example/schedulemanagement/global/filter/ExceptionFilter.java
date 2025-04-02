package org.example.schedulemanagement.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.example.schedulemanagement.global.exception.MyException;

import java.io.IOException;

public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (MyException e) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendError(e.getHttpStatus().value() , e.getMessage());
        }
    }
}

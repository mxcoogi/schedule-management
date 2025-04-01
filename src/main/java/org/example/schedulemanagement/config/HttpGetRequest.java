package org.example.schedulemanagement.config;

import jakarta.servlet.http.HttpServletRequest;
import org.example.schedulemanagement.dto.authdto.SavedSessionDto;
import org.springframework.stereotype.Component;

@Component
public class HttpGetRequest {
    public static Long getUserId(HttpServletRequest request){
        SavedSessionDto savedSessionDto = (SavedSessionDto)request.getSession().getAttribute(Const.LOGIN_USER);
        return savedSessionDto.getUserId();
    }
}

package org.example.schedulemanagement.global;

import jakarta.servlet.http.HttpServletRequest;
import org.example.schedulemanagement.dto.authdto.SavedSessionDto;



public abstract class AuthConst {

    public final static String LOGIN_USER = "loginUser";

    public static Long getUserId(HttpServletRequest request){
        SavedSessionDto savedSessionDto = (SavedSessionDto)request.getSession().getAttribute(LOGIN_USER);
        return savedSessionDto.getUserId();
    }
}

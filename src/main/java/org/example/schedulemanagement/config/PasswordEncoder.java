package org.example.schedulemanagement.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class PasswordEncoder {

    public String encode(String rawPassword){

        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    public boolean matchPassword(String rawPassword, String encodedPassword){
        BCrypt.Result verify = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return verify.verified;
    }
}

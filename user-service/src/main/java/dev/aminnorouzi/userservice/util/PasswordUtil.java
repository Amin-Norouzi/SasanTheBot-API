package dev.aminnorouzi.userservice.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    public String hash(String password) {
//        return BCrypt.hashpw(password, BCrypt.gensalt());
        return password;
    }

    public boolean validate(String password, String hashed) {
//        return BCrypt.checkpw(password, hashed);
        return false;
    }
}
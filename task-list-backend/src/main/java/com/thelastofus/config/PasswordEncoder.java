package com.thelastofus.config;


import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordEncoder {

    public String encode(String password) {
        return BcryptUtil.bcryptHash(password);
    }

}

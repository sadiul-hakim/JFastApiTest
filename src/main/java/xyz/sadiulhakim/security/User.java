package xyz.sadiulhakim.security;

import com.jFastApi.security.AuthUser;

import java.util.Collection;
import java.util.List;

public class User implements AuthUser {

    private String username = "Hakim";
    private String password = "hakim";
    private String role = "";

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<String> getRoles() {
        return List.of(role);
    }

    public User() {
    }
}

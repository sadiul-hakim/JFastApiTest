package xyz.sadiulhakim.security;

import com.jFastApi.security.AuthUser;

import java.util.Collection;
import java.util.List;

public class UserDetails implements AuthUser {

    private String username;
    private String password;
    private String role;

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

    public UserDetails() {
    }

    public UserDetails(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

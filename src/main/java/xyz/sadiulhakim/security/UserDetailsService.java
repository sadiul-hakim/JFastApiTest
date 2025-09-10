package xyz.sadiulhakim.security;

import com.jFastApi.annotation.Bean;
import com.jFastApi.exception.UsernameNotFoundException;
import com.jFastApi.security.AuthUser;
import com.jFastApi.security.AuthUserService;
import xyz.sadiulhakim.model.User;
import xyz.sadiulhakim.service.UserService;

@Bean
public class UserDetailsService implements AuthUserService {

    private final UserService userService;

    public UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new UserDetails(user.getUsername(), user.getPassword(), user.getRole());
    }
}

package xyz.sadiulhakim.security;

import com.jFastApi.annotation.Bean;
import com.jFastApi.exception.UsernameNotFoundException;
import com.jFastApi.security.AuthUser;
import com.jFastApi.security.AuthUserService;

@Bean
public class UserService implements AuthUserService {
    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User();
    }
}

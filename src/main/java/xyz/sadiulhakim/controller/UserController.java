package xyz.sadiulhakim.controller;

import com.jFastApi.annotation.Bean;
import com.jFastApi.annotation.HttpRoute;
import com.jFastApi.annotation.RequestBody;
import com.jFastApi.annotation.RequestParam;
import com.jFastApi.enumeration.HttpMethod;
import com.jFastApi.http.Response;
import xyz.sadiulhakim.model.User;
import xyz.sadiulhakim.service.UserService;

import java.util.List;
import java.util.Map;

@Bean
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @HttpRoute(path = "/user/save", method = HttpMethod.POST, roles = "ADMIN")
    public Response<Map> save(@RequestBody User user) {
        userService.save(user);

        return new Response.Builder<Map>()
                .body(Map.of("message", "Successfully saved user!"))
                .build();
    }

    @HttpRoute(path = "/user/find-all", method = HttpMethod.GET)
    public Response<List<User>> getAll() {
        List<User> users = userService.findAll();

        return new Response.Builder<List<User>>()
                .body(users)
                .build();
    }

    @HttpRoute(path = "/user/find-by-id", limit = 1)
    public Response<User> findUserById(@RequestParam(name = "id") long id) {
        User user = userService.findById(id);
        return new Response.Builder<User>()
                .body(user)
                .build();
    }

    @HttpRoute(path = "/user/delete", method = HttpMethod.DELETE, roles = "ADMIN")
    public Response<Map> delete(@RequestParam(name = "id") long id) {
        userService.delete(id);

        return new Response.Builder<Map>()
                .body(Map.of("message", "User deleted successfully."))
                .build();
    }
}

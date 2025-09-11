package xyz.sadiulhakim.controller;

import com.jFastApi.http.Response;
import com.jFastApi.http.interceptor.InterceptorRegistry;
import com.jFastApi.annotation.Bean;
import com.jFastApi.annotation.HttpRoute;
import com.jFastApi.annotation.RequestBody;
import com.jFastApi.annotation.RequestParam;
import com.jFastApi.enumeration.ContentType;
import com.jFastApi.enumeration.HttpMethod;
import com.jFastApi.enumeration.HttpStatus;
import com.jFastApi.exception.ApplicationException;
import xyz.sadiulhakim.model.Todo;
import xyz.sadiulhakim.service.TodoService;

import java.util.List;
import java.util.Map;

@Bean
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @HttpRoute(path = "/todo/save", method = HttpMethod.POST, roles = {"ADMIN"})
    public Response<Map> saveTodo(@RequestBody Todo todo) {

        todoService.save(todo);

        return new Response.Builder<Map>()
                .contentType(ContentType.JSON)
                .status(HttpStatus.OK)
                .body(Map.of("message", "Todo is saved successfully!"))
                .build();
    }

    @HttpRoute(path = "/todo/find-all", method = HttpMethod.GET, authorized = true)
    public Response<List> findAll(@RequestParam(name = "pageNumber", defaultValue = "0", required = false) long pageNumber) {
        InterceptorRegistry.getInterceptors();
        List<Todo> list = todoService.findAll();
        return new Response.Builder<List>()
                .contentType(ContentType.JSON)
                .status(HttpStatus.OK)
                .body(list)
                .build();
    }

    @HttpRoute(path = "/todo/delete", method = HttpMethod.DELETE)
    public Response<Map> delete(@RequestParam(name = "id") long id) {

        boolean deleted = todoService.delete(id);
        String msg = deleted ? "Deleted todo " + id + " successfully." : "Could not delete todo " + id;
        return new Response.Builder<Map>()
                .contentType(ContentType.JSON)
                .status(HttpStatus.OK)
                .body(Map.of("message", msg))
                .build();
    }

    @HttpRoute(path = "/todo/test", method = HttpMethod.GET)
    public Response<Map> test() {
        throw new ApplicationException("Test");
    }
}

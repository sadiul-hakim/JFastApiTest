package xyz.sadiulhakim.exception;

import com.jFastApi.annotation.Bean;
import com.jFastApi.annotation.ExceptionHandler;
import com.jFastApi.exception.ApplicationException;
import com.jFastApi.http.Response;
import com.jFastApi.http.enumeration.ContentType;
import com.jFastApi.http.enumeration.HttpStatus;

import java.util.Map;

@Bean
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = ApplicationException.class)
    public Response<Map> handleApplicationException(ApplicationException ex) {
        return new Response.Builder<Map>()
                .status(HttpStatus.BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body(Map.of("error", ex.getMessage()))
                .build();
    }
}

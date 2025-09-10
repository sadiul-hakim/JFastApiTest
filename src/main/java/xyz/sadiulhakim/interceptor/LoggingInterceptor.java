package xyz.sadiulhakim.interceptor;

import com.jFastApi.annotation.InterceptorBean;
import com.jFastApi.http.Route;
import com.jFastApi.http.interceptor.Interceptor;
import com.sun.net.httpserver.HttpExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@InterceptorBean(order = 1)
public class LoggingInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpExchange exchange, Route route) {
        LOGGER.info("Calling route : {} {}", route.method().name(), route.path());
        return true;
    }

    @Override
    public Object postHandle(HttpExchange exchange, Route route, Object result) {
        LOGGER.info("Done Calling route : {} {}", route.method().name(), route.path());
        return result;
    }

    @Override
    public boolean onException(HttpExchange exchange, Route route, Throwable ex) {
        LOGGER.error("Error Occurred , {}", ex.getMessage());
        return false;
    }
}

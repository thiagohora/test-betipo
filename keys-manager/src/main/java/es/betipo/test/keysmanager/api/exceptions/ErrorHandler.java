package es.betipo.test.keysmanager.api.exceptions;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import lombok.Data;

@Factory
public class ErrorHandler {

    @Data
    @Introspected
    public static class Error {
        private final String message;
    }

    @Produces
    @Singleton
    @Requires(classes = { NotFoundException.class, ExceptionHandler.class})
    public ExceptionHandler<NotFoundException, HttpResponse<Error>> notFoundException() {
        return (request, exception) -> HttpResponse.notFound(new Error(exception.getMessage()));
    }

}

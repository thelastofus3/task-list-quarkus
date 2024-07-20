package com.thelastofus.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.kafka.common.errors.InvalidRequestException;

import java.nio.file.AccessDeniedException;

@Provider
public class ExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        return switch (e) {
            case EmailAlreadyExistsException emailAlreadyExistsException ->
                    Response.status(Response.Status.CONFLICT).entity(ExceptionResponse.builder()
                            .message(e.getMessage())
                            .build()).build();
            case UsernameAlreadyExistsException usernameAlreadyExistsException ->
                    Response.status(Response.Status.CONFLICT).entity(ExceptionResponse.builder()
                            .message(e.getMessage())
                            .build()).build();
            case InvalidRequestException invalidRequestException ->
                    Response.status(Response.Status.BAD_REQUEST).entity(ExceptionResponse.builder()
                            .message(e.getMessage())
                            .build()).build();
            case ResourceNotFoundException resourceNotFoundException ->
                    Response.status(Response.Status.NOT_FOUND).entity(ExceptionResponse.builder()
                            .message(e.getMessage())
                            .build()).build();
            case IllegalStateException illegalStateException ->
                    Response.status(Response.Status.BAD_REQUEST).entity(ExceptionResponse.builder()
                            .message(e.getMessage())
                            .build()).build();
            case PasswordMatchesException passwordMatchesException ->
                    Response.status(Response.Status.CONFLICT).entity(ExceptionResponse.builder()
                            .message(e.getMessage())
                            .build()).build();
            case ConstraintViolationException constraintViolationException ->
                    Response.status(Response.Status.BAD_REQUEST).entity(ExceptionResponse.builder()
                            .message("Validation failed")
                            .build()).build();
            case null, default ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ExceptionResponse.builder()
                            .message(e.getMessage())
                            .build()).build();
        };
    }
}

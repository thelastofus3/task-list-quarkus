package com.thelastofus.exception;

import io.quarkus.security.AuthenticationFailedException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class AuthenticationExceptionHandler implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(ExceptionResponse.builder()
                        .message("Unauthorized")
                        .build())
                .build();
    }
}


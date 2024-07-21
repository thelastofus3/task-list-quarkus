package com.thelastofus.exception;

import io.quarkus.security.AuthenticationFailedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMapper {

    @ServerExceptionMapper(value = AuthenticationFailedException.class, priority = Priorities.USER)
    public Response toResponse(Exception exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();

    }

}

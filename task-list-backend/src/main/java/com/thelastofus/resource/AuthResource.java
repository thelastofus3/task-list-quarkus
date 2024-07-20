package com.thelastofus.resource;

import com.thelastofus.dto.jwt.JwtRequest;
import com.thelastofus.dto.user.UserRequest;
import com.thelastofus.service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
@Path("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "User Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthResource {

    AuthService authService;

    @POST
    @PermitAll
    @Path("/login")
    public RestResponse<?> login(JwtRequest loginRequest) {
        return RestResponse.ok(authService.login(loginRequest));
    }

    public RestResponse<?> register(UserRequest userRequest) {
        return RestResponse.status(RestResponse.Status.CREATED,
                authService.create(userRequest));
    }
}

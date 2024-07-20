package com.thelastofus.resource;

import com.thelastofus.service.UserService;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
@Path("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Resource")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserResource {

    UserService userService;
    SecurityIdentity securityIdentity;


    @GET
    @RolesAllowed("ROLE_USER")
    @Operation(summary = "Get user")
    public RestResponse<?> getUser() {
        return RestResponse.ok(userService.getByUsername(securityIdentity.getPrincipal().getName()));
    }


}

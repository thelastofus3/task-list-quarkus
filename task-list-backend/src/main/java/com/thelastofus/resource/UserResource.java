package com.thelastofus.resource;

import com.thelastofus.service.UserService;
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


//    @GET
//    @Operation(summary = "Get user")
//    public RestResponse<?> getUser(@AuthenticationPrincipal JwtEntity jwt) {
//        return ResponseEntity.ok(userService.getByUsername(jwt.getUsername()));
//    }

    @GET
    public RestResponse<?> getUser(Long id) {
        return RestResponse.ok(userService.getById(id));
    }

}

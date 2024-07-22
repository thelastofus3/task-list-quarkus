package com.thelastofus.resource;

import com.thelastofus.dto.task.CreateTaskRequest;
import com.thelastofus.dto.task.UpdateTaskRequest;
import com.thelastofus.service.TaskService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
@RequiredArgsConstructor
@Path("/api/v1/tasks")
@RolesAllowed("ROLE_USER")
@Tag(name = "Task Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskResource {

    TaskService taskService;
    JsonWebToken jsonWebToken;


    @GET
    @Operation(summary = "Get user tasks")
    public RestResponse<?> getTasks() {
        return RestResponse.ok(taskService.getByUsername(
                jsonWebToken.getName())
        );
    }

    @POST
    @Operation(summary = "Create task")
    public RestResponse<?> createTask(@Valid CreateTaskRequest createTaskRequest) {
        return RestResponse.status(RestResponse.Status.CREATED,
                taskService.create(createTaskRequest,
                        jsonWebToken.getClaim("email")));
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete task")
    public RestResponse<?> deleteTask(Long id) {
        return RestResponse.ok(taskService.delete(id,
                jsonWebToken.getClaim("email")));
    }

    @PATCH
    @Operation(summary = "Update task")
    public RestResponse<?> updateTask(@Valid UpdateTaskRequest updateTaskRequest) {
         return RestResponse.ok(taskService.update(updateTaskRequest,
                 jsonWebToken.getClaim("email")));
    }

}
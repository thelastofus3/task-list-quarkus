package com.thelastofus.dto.task;

import com.thelastofus.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Create Task Request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTaskRequest {

    @Schema(name = "title", example = "Write a book")
    @NotBlank(message = "Title should not be empty")
    String title;
    @Schema(name = "description", example = "Fantasy about adventures on the island")
    String description;
    @Schema(name = "status", example = "TODO")
    @NotNull(message = "Status should not be empty")
    Status status;

}

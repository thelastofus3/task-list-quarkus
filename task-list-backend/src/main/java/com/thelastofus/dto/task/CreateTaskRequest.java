package com.thelastofus.dto.task;

import jakarta.validation.constraints.NotBlank;
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

}

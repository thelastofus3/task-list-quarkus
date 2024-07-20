package com.thelastofus.dto.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thelastofus.model.Status;
import com.thelastofus.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Task Response")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {

    @Schema(name = "id", example = "1")
    Long id;
    @Schema(name = "title", example = "Write a book")
    String title;
    @Schema(name = "description", example = "Fantasy about adventures on the island")
    String description;
    @Schema(name = "created_at", example = "2024-06-16 16:03:50")
    LocalDateTime created_at;
    @Schema(name = "status", example = "TODO")
    Status status;

}

package com.thelastofus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task implements Serializable {

    @Schema(name = "id", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Schema(name = "title", example = "Write a book")
    @Column(nullable = false)
    String title;
    @Schema(name = "description", example = "Fantasy about adventures on the island")
    String description;
    @ManyToOne
    @Builder.Default
    @JsonBackReference
    @JoinColumn(name = "user_id")
    User owner = new User();
    @Schema(name = "created_at", example = "2024-06-16 16:03:50")
    @Column(nullable = false)
    LocalDateTime created_at;
    @Schema(name = "status", example = "TODO")
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Status status;

}

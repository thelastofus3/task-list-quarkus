package com.thelastofus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String title;
    String description;
    @ManyToOne
    @Builder.Default
    @JsonBackReference
    @JoinColumn(name = "user_id")
    User owner = new User();
    @Column(nullable = false)
    LocalDateTime created_at;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Status status;

}

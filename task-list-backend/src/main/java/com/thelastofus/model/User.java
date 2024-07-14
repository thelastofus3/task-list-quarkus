package com.thelastofus.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thelastofus.password.CustomPasswordProvider;
import io.quarkus.security.jpa.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@UserDefinition
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Schema(description = "User")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    @Schema(name = "id", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Username
    @Schema(name = "username", example = "thelastofus")
    @Column(nullable = false, unique = true, length = 120)
    String username;
    @Schema(name = "email", example = "user@gmail.com")
    @Column(nullable = false, unique = true)
    String email;
    @Password(value = PasswordType.CUSTOM, provider = CustomPasswordProvider.class)
    @Schema(name = "password", example = "password")
    @Column(nullable = false, length = 120)
    String password;
    @Schema(name = "role", example = "ROLE_USER")
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Role role;
    @Singular
    @JsonManagedReference
    @OneToMany(mappedBy = "owner")
    List<Task> tasks = new ArrayList<>();

}

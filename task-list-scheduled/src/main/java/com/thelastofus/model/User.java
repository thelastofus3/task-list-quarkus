package com.thelastofus.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true, length = 120)
    String username;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false, length = 120)
    String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Role role;
    @Singular
    @JsonManagedReference
    @OneToMany(mappedBy = "owner")
    List<Task> tasks = new ArrayList<>();

}

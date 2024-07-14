package com.thelastofus.repository;

import com.thelastofus.model.Task;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {

    List<Task> findAllByUsername(String username) {
        return find("username", username).list();
    }

    boolean existsByIdAndOwnerId(Long taskId, Long ownerId) {
        return find("id = :taskId and ownerId = :ownerId", Parameters.with("taskId", taskId).and("ownerId", ownerId)).firstResult() != null;
    }

}

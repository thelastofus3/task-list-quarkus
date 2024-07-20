package com.thelastofus.repository;

import com.thelastofus.model.Task;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {

    public List<Task> findAllByUsername(String username) {
        return find("SELECT t FROM Task t WHERE t.owner.username = :username",
                Parameters.with("username", username)).list();
    }

    public boolean existsByIdAndOwnerId(Long taskId, Long ownerId) {
        return find("SELECT t FROM Task t WHERE t.id = :taskId AND t.owner.id = :ownerId",
                Parameters.with("taskId", taskId)
                        .and("ownerId", ownerId)).firstResult() != null;
    }

    public Task save(Task task) {
        persist(task);
        return task;
    }

}

package com.stabbers.semenov.repository;

import com.stabbers.semenov.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByTitle(String title);
    Task findById(int taskId);
    List<Task> findByProjectId(int projectId);
}

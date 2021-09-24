package com.stabbers.semenov.service;

import com.stabbers.semenov.model.Task;
import com.stabbers.semenov.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(Task newTask) {
        taskRepository.save(newTask);
    }

    public Task findByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    public Task findById(int taskId) {
        return taskRepository.findById(taskId);
    }

    public List<Task> getByProjectId(int projectId) {
        return taskRepository.findByProjectId(projectId);
    }
}

package com.stabbers.semenov.service;

import com.stabbers.semenov.model.TaskHours;
import com.stabbers.semenov.repository.TaskHoursRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskHoursService {
    final
    TaskHoursRepository taskHoursRepository;

    public TaskHoursService(TaskHoursRepository taskHoursRepository) {
        this.taskHoursRepository = taskHoursRepository;
    }

    public void save(TaskHours taskHours){
        taskHoursRepository.save(taskHours);
    }

    public TaskHours findByTaskId(int taskId){
        return taskHoursRepository.findByTaskId(taskId);
    }
}

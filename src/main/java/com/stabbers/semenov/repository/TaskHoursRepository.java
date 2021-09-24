package com.stabbers.semenov.repository;

import com.stabbers.semenov.model.Project;
import com.stabbers.semenov.model.TaskHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskHoursRepository extends JpaRepository<TaskHours, Integer> {
    TaskHours findByTaskId(int taskId);
}

package com.stabbers.semenov.repository;

import com.stabbers.semenov.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByName(String name);
    Project findById(int id);
    List<Project> findAllByHolder_Id(int holder_id);
}

package com.stabbers.semenov.service;

import com.stabbers.semenov.model.Project;
import com.stabbers.semenov.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    final
    ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void save(Project newProject) {
        projectRepository.save(newProject);
    }

    public Project findById(int projectId){
        return projectRepository.findById(projectId);
    }

//    public void changeStatus(Project project, int status) {
//        project.setStatus(status);
//        save(project);
//    }
//
//    public Project findByName(String name) {
//        return projectRepository.findByName(name);
//    }
//
//    public List<Project> getAll() {
//        return projectRepository.findAll();
//    }
//
//    public List<Project> getAllByHolderId(int holderId) {
//        return projectRepository.findAllByHolder_Id(holderId);
//    }

}

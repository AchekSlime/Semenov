package com.stabbers.semenov.service;

import com.stabbers.semenov.model.Project;
import com.stabbers.semenov.model.User;
import com.stabbers.semenov.model.UserProject;
import com.stabbers.semenov.repository.UserProjectRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserProjectService {
    private final UserProjectRepository userProjectRepository;
    private final ProjectService projectService;

    public UserProjectService(UserProjectRepository userProjectRepository, ProjectService projectService) {
        this.userProjectRepository = userProjectRepository;
        this.projectService = projectService;
    }

    public void save(User user, Project project) {
        project.setUserCount(project.getUserCount() + 1);
        projectService.save(project);
        userProjectRepository.save(new UserProject(user, project));
    }



    public List<Project> getAllProjectsByUserId(int userId) {
        List<UserProject> userProjects = userProjectRepository.findAllByUserId(userId);
        LinkedList<Project> ans = new LinkedList<>();
        userProjects.forEach((up) -> ans.add(up.getProject()));
        return ans;
    }
}

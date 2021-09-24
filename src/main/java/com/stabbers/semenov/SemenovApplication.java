package com.stabbers.semenov;

import com.stabbers.semenov.model.Project;
import com.stabbers.semenov.model.Task;
import com.stabbers.semenov.model.User;
import com.stabbers.semenov.service.ProjectService;
import com.stabbers.semenov.service.TaskService;
import com.stabbers.semenov.service.UserProjectService;
import com.stabbers.semenov.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.sql.Timestamp;

@SpringBootApplication
public class SemenovApplication {
    private static final Logger logger = LoggerFactory.getLogger(SemenovApplication.class);

    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserProjectService userProjectService;


    public SemenovApplication(UserService userService, TaskService taskService, ProjectService projectService, UserProjectService userProjectService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.userProjectService = userProjectService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SemenovApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        User creator = createUser("Creator");
        User user = createUser("User");

        Project project1 = createProject("Project-1", creator);

        //project.getUsers().add(secUser);
        //user.getProjects().add(project);

        Project project2 = createProject("Project-2", null);
        createProject("Project-3", creator);

        userProjectService.save(user, project1);
        userProjectService.save(user, project2);

        createTask("Task-1", project1);
    }

    private User createUser(String login){
        User newUser = new User(login, "12345","Achek", "Slime", 1000);
        userService.save(newUser);
        logger.info("User creation was done!");
        return newUser;
    }

    private User findUser(String login){
        User user = userService.findByLogin(login);
        logger.info("User finding was done!");
        return user;
    }

    private void createTask(String title, Project project){
        User holder = findUser("Achek");
        Task newTask = new Task(title, "Description", 0, null, holder, project, new Timestamp(1000), new Timestamp(2000));
        taskService.save(newTask);
        logger.info("Task creation was done!");
    }

    private Task findTask(String title){
        Task task = taskService.findByTitle(title);
        logger.info("Task finding was done!");
        return task;
    }

    private Project createProject(String name, User holder){
        Project newProject = new Project(name, "Description", 0, holder, null, null);
        projectService.save(newProject);
        return newProject;
    }

}

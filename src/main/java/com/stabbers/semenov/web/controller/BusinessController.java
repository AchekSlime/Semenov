package com.stabbers.semenov.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.stabbers.semenov.model.TaskHours;
import com.stabbers.semenov.model.User;
import com.stabbers.semenov.model.json.Views;
import com.stabbers.semenov.service.*;
import com.stabbers.semenov.web.Utils;
import com.stabbers.semenov.web.json.*;
import com.stabbers.semenov.model.Project;
import com.stabbers.semenov.model.Task;
import com.stabbers.semenov.web.security.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessController {
    private final ProjectService projectService;
    private final TaskService taskService;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserProjectService userProjectService;
    private final TaskHoursService taskHoursService;

    public BusinessController(ProjectService projectService, TaskService taskService, JwtProvider jwtProvider, UserService userService, UserProjectService userProjectService, TaskHoursService taskHoursService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.userProjectService = userProjectService;
        this.taskHoursService = taskHoursService;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getUsers(@RequestHeader("Authorization") String bearer) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            user = userService.findByLogin(userLogin);
        }
        if (user == null)
            return null;

        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/projects")
    public ResponseEntity<Iterable<Project>> getProjects(@RequestHeader("Authorization") String bearer) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            user = userService.findByLogin(userLogin);
        }
        if (user == null)
            return null;

        List<Project> projects = userProjectService.getAllProjectsByUserId(user.getId());
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @JsonView({Views.forList.class})
    @GetMapping("/tasks")
    public ResponseEntity<Iterable<Task>> getTasks(@RequestHeader("Authorization") String bearer, @RequestBody GetTasksRequest request) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token == null && !jwtProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String userLogin = jwtProvider.getLoginFromToken(token);
        user = userService.findByLogin(userLogin);

        if (user == null)
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        List<Task> tasks = taskService.getByProjectId(request.getProjectId());
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @JsonView({Views.forList.class})
    @GetMapping("/project/get")
    public ResponseEntity<Project> getProject(@RequestHeader("Authorization") String bearer, @RequestBody GetProjectRequest request) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            user = userService.findByLogin(userLogin);
        }
        if (user == null)
            return null;

        return new ResponseEntity<>(projectService.findById(request.getProjectId()), HttpStatus.OK);
    }

    @JsonView({Views.forList.class})
    @GetMapping("/task/get")
    public ResponseEntity<Task> getTask(@RequestHeader("Authorization") String bearer, @RequestBody GetTaskRequest request) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token == null && !jwtProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String userLogin = jwtProvider.getLoginFromToken(token);
        user = userService.findByLogin(userLogin);

        if (user == null)
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(taskService.findById(request.getTaskId()), HttpStatus.OK);
    }

    @JsonView({Views.forList.class})
    @PostMapping("/project/add")
    public HttpStatus addProject(@RequestHeader("Authorization") String bearer, @RequestBody AddProjectRequest request) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            user = userService.findByLogin(userLogin);
        }
        if (user == null)
            return null;

        // @ToDo поставить дату.
        Project newProject = new Project(request.getName(), request.getDescription(), 0, user, null, null);
        projectService.save(newProject);
        userProjectService.save(user, newProject);
        request.getUsers().forEach((usr) -> userProjectService.save(userService.findByLogin(usr), newProject));

        return HttpStatus.OK;
    }

    @JsonView({Views.forList.class})
    @PostMapping("/task/add")
    public HttpStatus addTask(@RequestHeader("Authorization") String bearer, @RequestBody AddTaskRequest request) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            user = userService.findByLogin(userLogin);
        }
        if (user == null)
            return null;

        // @ToDo поставить дату.
        User worker = userService.findById(request.getWorkerId());
        Project project = projectService.findById(request.getProjectId());
        Task newTask = new Task(request.getTitle(), request.getDescription(), 0, worker, user, project, null, null);
        taskService.save(newTask);
        return HttpStatus.OK;
    }

    @JsonView({Views.forList.class})
    @PostMapping("/hours")
    public HttpStatus hours(@RequestHeader("Authorization") String bearer, @RequestBody HoursRequest request) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            user = userService.findByLogin(userLogin);
        }
        if (user == null)
            return null;

        Task task = taskService.findById(request.getTaskId());
        TaskHours hours = new TaskHours(user, task, request.getHours());
        taskHoursService.save(hours);
        return HttpStatus.OK;
    }

    @JsonView({Views.forList.class})
    @GetMapping("/graph")
    public HttpStatus getGraph(@RequestHeader("Authorization") String bearer, @RequestBody GetGraphRequest request) {
        String token = Utils.getTokenFromHeader(bearer);

        User user = null;
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            user = userService.findByLogin(userLogin);
        }
        if (user == null)
            return null;

        return HttpStatus.OK;
    }

}

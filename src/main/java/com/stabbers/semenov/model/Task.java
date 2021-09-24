package com.stabbers.semenov.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.stabbers.semenov.model.json.Views;
import lombok.Data;

import javax.persistence.*;

@Table(name = "task")
@Entity
@Data
public class Task {
    @JsonView({Views.forList.class})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @JsonView({Views.forList.class})
    @Column(unique = true, columnDefinition = "VARCHAR(128)")
    private String title;

    @JsonView({Views.forList.class})
    @Column
    // ToDo поменять на <текст>.
    private String description;

    @JsonView({Views.forList.class})
    @Column
    private int status;

    @JsonView({Views.forList.class})
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private User worker;

    @JsonView({Views.forList.class})
    @ManyToOne
    @JoinColumn(name = "holder_id")
    private User holder;

    @JsonView({Views.fullMessage.class})
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonView({Views.forList.class})
    @Column
    private java.sql.Timestamp dateStart;

    @JsonView({Views.forList.class})
    @Column
    private java.sql.Timestamp dateFinish;

    public Task() {
    }

    public Task(String title, String description, int status, User worker, User holder, Project project, java.sql.Timestamp dateStart, java.sql.Timestamp dateFinish) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.worker = worker;
        this.holder = holder;
        this.project = project;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

}

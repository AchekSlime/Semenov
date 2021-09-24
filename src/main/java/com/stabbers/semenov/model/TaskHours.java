package com.stabbers.semenov.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.stabbers.semenov.model.json.Views;
import lombok.Data;

import javax.persistence.*;

@Table(name = "task_hours")
@Entity
@Data
public class TaskHours {
    @JsonView({Views.forList.class})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @JsonView({Views.forList.class})
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private User worker;

    @JsonView({Views.forList.class})
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column
    private int hours;

    public TaskHours(){}

    public TaskHours(User worker, Task task, int hours){
        this.hours = hours;
        this.worker = worker;
        this.task = task;
    }
}

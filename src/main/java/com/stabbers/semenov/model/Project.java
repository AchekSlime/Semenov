package com.stabbers.semenov.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.stabbers.semenov.model.json.Views;
import lombok.Data;

import javax.persistence.*;

@Table(name = "project")
@Entity
@Data
public class Project {
    @JsonView({Views.forList.class})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @JsonView({Views.forList.class})
    @Column(unique = true, columnDefinition = "VARCHAR(128)")
    private String name;

    @JsonView({Views.forList.class})
    @Column
    // ToDo поменять на <текст>.
    private String description;

    @JsonView({Views.forList.class})
    @Column
    private int status;

    @JsonView({Views.fullMessage.class})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User holder;

    @JsonView({Views.forList.class})
    @Column
    private java.sql.Timestamp dateStart;

    @JsonView({Views.forList.class})
    @Column
    private java.sql.Timestamp dateFinish;

    @JsonView({Views.forList.class})
    @Column
    private int userCount;

    public Project() {

    }

    public Project(String name, String description, int status, User holder, java.sql.Timestamp dateStart, java.sql.Timestamp dateFinish) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.holder = holder;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.userCount = 0;
    }

}

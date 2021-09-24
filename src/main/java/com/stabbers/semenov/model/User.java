package com.stabbers.semenov.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.stabbers.semenov.model.json.Views;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Entity
@Data
public class User {
    @JsonView({Views.forList.class})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @JsonView({Views.forList.class})
    @Column(unique = true, columnDefinition = "VARCHAR(30)")
    private String login;

    @JsonView({Views.forList.class})
    @Column(columnDefinition = "VARCHAR(30)")
    private String password;

    @JsonView({Views.forList.class})
    @Column(columnDefinition = "VARCHAR(30)")
    private String name;

    @JsonView({Views.forList.class})
    @Column(columnDefinition = "VARCHAR(30)")
    private String surname;

    //    @Column(name = "salary_per_hours")
    @JsonView({Views.forList.class})
    @Column
    private double salaryPerHours;

//    @JsonView({Views.fullMessage.class})
//    @ManyToMany()
//    @JoinTable(name = "user_project", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
//    private Set<Project> projects = new HashSet<>();

    public User() {
    }

    public User(String login, String password, String name, String surname, double salaryPerHours) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.salaryPerHours = salaryPerHours;
    }
}

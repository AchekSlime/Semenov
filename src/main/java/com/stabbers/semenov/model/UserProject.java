package com.stabbers.semenov.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.stabbers.semenov.model.json.Views;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_project")
public class UserProject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JsonView({Views.forList.class})
    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @JsonView({Views.forList.class})
    @ManyToOne
    @JoinColumn (name="project_id")
    private Project project;

    public UserProject(){

    }

    public UserProject(User user, Project project){
        this.user = user;
        this.project = project;
    }
}

package com.stabbers.semenov.web.json;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddTaskRequest {
    @NotEmpty
    private String title;
    private String description;
    private int workerId;
    private int projectId;
}

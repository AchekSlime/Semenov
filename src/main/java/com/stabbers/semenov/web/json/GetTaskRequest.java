package com.stabbers.semenov.web.json;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GetTaskRequest {
    @NotEmpty
    private int taskId;
}

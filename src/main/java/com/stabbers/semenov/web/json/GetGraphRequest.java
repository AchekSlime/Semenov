package com.stabbers.semenov.web.json;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GetGraphRequest {
    @NotEmpty
    int projectId;
}

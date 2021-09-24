package com.stabbers.semenov.web.json;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AddProjectRequest {
    @NotEmpty
    String name;

    String description;

    List<String> users;
}

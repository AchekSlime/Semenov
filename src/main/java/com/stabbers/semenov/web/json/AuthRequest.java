package com.stabbers.semenov.web.json;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}

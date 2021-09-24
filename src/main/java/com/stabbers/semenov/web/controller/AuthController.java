package com.stabbers.semenov.web.controller;

import com.stabbers.semenov.web.json.AuthRequest;
import com.stabbers.semenov.model.User;
import com.stabbers.semenov.web.security.JwtProvider;
import com.stabbers.semenov.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    final
    UserService userService;
    final
    JwtProvider jwtProvider;

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/auth")
    public String auth(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        return jwtProvider.generateToken(user.getLogin());
    }
}

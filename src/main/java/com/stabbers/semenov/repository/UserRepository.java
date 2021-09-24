package com.stabbers.semenov.repository;

import com.stabbers.semenov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    User findById(int id);
    User findByLoginAndPassword(String login, String password);
}

package com.stabbers.semenov.service;

import com.stabbers.semenov.model.User;
import com.stabbers.semenov.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User newUser){
        userRepository.save(newUser);
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public User findById(int id) {return userRepository.findById(id);}

    public User findByLoginAndPassword(String login, String password){
        return userRepository.findByLoginAndPassword(login, password);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}

package com.stabbers.semenov.repository;

import com.stabbers.semenov.model.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject, Integer> {
    List<UserProject> findAllByUserId(int userId);
}

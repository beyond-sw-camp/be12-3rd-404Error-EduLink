package com.example.package404.manager.repository;

import com.example.package404.user.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<User, Long> {
    @Override
    @EntityGraph(attributePaths = {"studentDetail", "boards"})
    List<User> findAll();
}

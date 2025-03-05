package com.example.package404.user.repository;

import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String name);

    List<User> findByRoleEquals(String roleUser);

    List<User> findByRole(String role);
}

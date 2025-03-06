package com.example.package404.student.repository;

import com.example.package404.student.model.StudentDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentDetail, Long> {

    @Query("SELECT sd FROM StudentDetail sd JOIN FETCH sd.user u")
    Page<StudentDetail> findAllStudents(PageRequest of);

    @Query("SELECT sd FROM StudentDetail sd JOIN FETCH sd.user u WHERE sd.idx = :idx")
    Optional<StudentDetail> findByStudent(Long idx);
}
package com.example.package404.instructor.repository;


import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository  extends JpaRepository<Instructor, Long> {



//    @Query("SELECT i FROM Instructor i JOIN FETCH i.user WHERE i.userIdx = :userIdx")
//    Optional<Instructor> findByIdWithUser(@Param("userIdx") Long userIdx);

    @Query("SELECT DISTINCT i FROM Instructor i JOIN FETCH i.user u WHERE i.userIdx = :userIdx")
    Optional<Instructor> findDistinctInstructorByUserIdx(@Param("userIdx") Long userIdx);


    @Query("SELECT i FROM Instructor i JOIN FETCH i.user WHERE i.id = :instructorId")
    Optional<Instructor> findInstructorWithUser(@Param("instructorId") Long instructorId);

    Optional<Instructor> findById(Long userIdx);


    Optional<Object> findByUser(User user);
}

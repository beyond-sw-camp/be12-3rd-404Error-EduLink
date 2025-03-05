package com.example.package404.student.service;

import com.example.package404.global.exception.StudentException;
import com.example.package404.global.response.responseStatus.StudentResponseStatus;
import com.example.package404.student.model.Dto.*;
import com.example.package404.student.model.StudentDetail;
import com.example.package404.student.repository.StudentRepository;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentDetailResponseDto register(StudentDetailRegisterDto dto, User user) {
        if (dto == null) {
            throw new StudentException(StudentResponseStatus.STUDENT_NOT_FOUND);
        }

        try {
            StudentDetail studentDetail = studentRepository.save(dto.toEntity(user));
            return StudentDetailResponseDto.from(studentDetail);
        } catch (Exception e) {
            throw new StudentException(StudentResponseStatus.STUDENT_ENROLLMENT_FAILED);
        }
    }

    public StudentResponseDto read(Long idx) {
        if (idx == null || idx < 0) {
            throw new StudentException(StudentResponseStatus.INVALID_STUDENT_ID);
        }

        StudentDetail student = studentRepository.findByStudent(idx).orElseThrow();
        if (student == null) {
            throw new StudentException(StudentResponseStatus.STUDENT_NOT_FOUND);
        }

        return StudentResponseDto.from(student);
    }

    public StudentDetailPageResponse list(int page, int size) {
        if (page < 0 || page+1 > studentRepository.count() || size < 0 || size > studentRepository.count()) {
            throw new StudentException(StudentResponseStatus.INVALID_PAGE);
        }
        Page<StudentDetail> studentList = studentRepository.findAllStudents(PageRequest.of(page, size));
        if (studentList.isEmpty()) {
            throw new StudentException(StudentResponseStatus.STUDENT_NOT_FOUND);
        }
        return StudentDetailPageResponse.from(studentList);
    }

    public StudentDetailResponseDto update(User user, String action) {
        if (user.getIdx() == null || user.getIdx() < 0) {
            throw new StudentException(StudentResponseStatus.INVALID_STUDENT_ID);
        }
        User findUser = userRepository.findById(user.getIdx()).orElseThrow();
        if (findUser.getStudentDetail().getIdx() == null || findUser.getStudentDetail().getIdx() < 0) {
            throw new StudentException(StudentResponseStatus.INVALID_STUDENT_ID);
        }
        StudentDetail studentDetail = studentRepository.findById(findUser.getStudentDetail().getIdx()).orElseThrow();

        if (action.equals("testStatus")) {
            studentDetail.updateTestStatus();
            StudentDetail studentDetailR = studentRepository.save(studentDetail);
            return StudentDetailResponseDto.from(studentDetailR);
        } else if (action.equals("perception")) {
            studentDetail.updatePerception();
            StudentDetail studentDetailR = studentRepository.save(studentDetail);
            return StudentDetailResponseDto.from(studentDetailR);
        } else if (action.equals("attendance")) {
            studentDetail.updateAttendance();
            StudentDetail studentDetailR = studentRepository.save(studentDetail);
            return StudentDetailResponseDto.from(studentDetailR);
        } else if (action.equals("leaveEarly")) {
            studentDetail.updateLeaveEarly();
            StudentDetail studentDetailR = studentRepository.save(studentDetail);
            return StudentDetailResponseDto.from(studentDetailR);
        } else if (action.equals("outing")) {
            studentDetail.updateOuting();
            StudentDetail studentDetailR = studentRepository.save(studentDetail);
            return StudentDetailResponseDto.from(studentDetailR);
        } else if (action.equals("vacationLeft")) {
            studentDetail.updateVacationLeft();
            StudentDetail studentDetailR = studentRepository.save(studentDetail);
            return StudentDetailResponseDto.from(studentDetailR);
        } else {
            throw new StudentException(StudentResponseStatus.INVALID_UPDATE_ACTION);
        }
    }
}

package com.example.package404.manager.service;

import com.example.package404.global.exception.ManagerException;
import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.global.response.responseStatus.ManagerResponseStatus;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.manager.model.Test;
import com.example.package404.manager.model.dto.ManagerResponseDto;
import com.example.package404.manager.model.dto.TestRequestDto;
import com.example.package404.manager.model.dto.TestResponseDto;
import com.example.package404.manager.repository.ManagerRepository;
import com.example.package404.manager.repository.TestRepository;
import com.example.package404.student.model.Dto.StudentDetailResponseDto;
import com.example.package404.student.model.StudentDetail;
import com.example.package404.student.repository.StudentRepository;
import com.example.package404.user.model.Dto.UserResponseDto;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final ManagerRepository managerRepository;
    private final TestRepository testRepository;

    @Transactional(readOnly = true)
    public BaseResponse<List<ManagerResponseDto>> getManagerList() {
        List<User> managerList = managerRepository.findAll();
        if (managerList.isEmpty()) {
            throw new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND);
        }
        List<ManagerResponseDto> response = managerList.stream()
                .map(ManagerResponseDto::of)
                .toList();
        return new BaseResponse<>(true, CommonResponseStatus.SUCCESS.getMessage(),
                CommonResponseStatus.SUCCESS.getCode(), response);
    }

    @Transactional(readOnly = true)
    public BaseResponse<ManagerResponseDto> getManager(Long managerIdx) {
        User manager = managerRepository.findById(managerIdx)
                .orElseThrow(() -> new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND));
        return new BaseResponse<>(true, CommonResponseStatus.SUCCESS.getMessage(),
                CommonResponseStatus.SUCCESS.getCode(), ManagerResponseDto.of(manager));
    }

    @Transactional(readOnly = true)
    public BaseResponse<UserResponseDto.SignupResponse> getUser(Long userIdx) {
        User user = userRepository.findById(userIdx)
                .orElseThrow(() -> new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND));
        return new BaseResponse<>(true, CommonResponseStatus.SUCCESS.getMessage(),
                CommonResponseStatus.SUCCESS.getCode(), UserResponseDto.SignupResponse.from(user));
    }

//    @Transactional(readOnly = true)
//    public BaseResponse<InstructorResponseDto> getInstructorByEmail(String instructorEmail) {
//        // Assuming you fixed the repository method to find by userEmail
//        Instructor instructor = instructorRepository.findByUserEmail(instructorEmail)
//                .orElseThrow(() -> new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND));
//        return new BaseResponse<>(true, CommonResponseStatus.SUCCESS.getMessage(),
//                CommonResponseStatus.SUCCESS.getCode(), InstructorResponseDto.from(instructor));
//    }

    @Transactional(readOnly = true)
    public BaseResponse<List<InstructorResponseDto>> getInstructorList() {
        List<Instructor> instructorList = instructorRepository.findAll();
        if (instructorList.isEmpty()) {
            throw new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND);
        }
        List<InstructorResponseDto> response = instructorList.stream()
                .map(InstructorResponseDto::from)
                .toList();
        return new BaseResponse<>(true, CommonResponseStatus.SUCCESS.getMessage(),
                CommonResponseStatus.SUCCESS.getCode(), response);
    }

    @Transactional(readOnly = true)
    public BaseResponse<List<StudentDetailResponseDto>> getStudentList() {
        List<StudentDetail> studentList = studentRepository.findAll();
        if (studentList.isEmpty()) {
            throw new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND);
        }
        List<StudentDetailResponseDto> response = studentList.stream()
                .map(StudentDetailResponseDto::from)
                .toList();
        return new BaseResponse<>(true, CommonResponseStatus.SUCCESS.getMessage(),
                CommonResponseStatus.SUCCESS.getCode(), response);
    }

    @Transactional
    public BaseResponse<TestResponseDto> registerTest(TestRequestDto dto) {
        Test test = testRepository.save(dto.toEntity());
        return new BaseResponse<>(true, "테스트 등록 성공",
                CommonResponseStatus.SUCCESS.getCode(), TestResponseDto.of(test));
    }

    @Transactional
    public BaseResponse<TestResponseDto> updateTest(Long testIdx, TestRequestDto dto) {
        Test test = testRepository.findById(testIdx)
                .orElseThrow(() -> new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND));
        test.setTitle(dto.getTitle());
        test.setContent(dto.getContent());
        testRepository.save(test);
        return new BaseResponse<>(true, "테스트 수정 성공",
                CommonResponseStatus.SUCCESS.getCode(), TestResponseDto.of(test));
    }

    @Transactional
    public BaseResponse<TestResponseDto> deleteTest(Long testIdx) {
        Test test = testRepository.findById(testIdx)
                .orElseThrow(() -> new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND));
        testRepository.delete(test);
        return new BaseResponse<>(true, "테스트 삭제 성공",
                CommonResponseStatus.SUCCESS.getCode(), TestResponseDto.of(test));
    }

    @Transactional(readOnly = true)
    public BaseResponse<List<TestResponseDto>> getTestList() {
        List<Test> testList = testRepository.findAllWithCourse();
        if (testList.isEmpty()) {
            throw new ManagerException(ManagerResponseStatus.MANAGER_NOT_FOUND);
        }
        List<TestResponseDto> response = testList.stream()
                .map(TestResponseDto::of)
                .toList();
        return new BaseResponse<>(true, CommonResponseStatus.SUCCESS.getMessage(),
                CommonResponseStatus.SUCCESS.getCode(), response);
    }

}

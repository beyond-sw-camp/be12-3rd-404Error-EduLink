package com.example.package404.instructor.service;


import com.example.package404.global.exception.InstructorException;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.req.InstructorRequestDto;
import com.example.package404.instructor.model.dto.req.UpdateUserInstructorDto;
import com.example.package404.instructor.model.dto.res.InstructorIdDto;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.user.model.User;
import com.example.package404.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final UserService userService;


    //    //Todo 강사 조회
    public InstructorResponseDto getInstructor(Long instructorIdx) {
        try {
            User user = userService.getUserinfo(instructorIdx); // User 조회
            Instructor instructor = instructorRepository.findById(user.getIdx()) // Instructor 조회
                    .orElseThrow(() -> new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_FOUND));
            return InstructorResponseDto.from(instructor, user);
        } catch (InstructorException e) {
            // 예외 처리 로직: 강사를 찾을 수 없는 경우
            throw new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_FOUND);
        } catch (Exception e) {
            // 그 외 예외 처리
            throw new InstructorException(InstructorResponseStatus.UNKNOWN_ERROR);
        }
    }

    // 그냥 서비스로직 내 idx 값 조회
    public Instructor getInstructorId(Long userIdx) {
        try {
            Optional<Instructor> instructor = instructorRepository.findDistinctInstructorByUserIdx(userIdx);
            if (instructor.isPresent()) {
                return InstructorIdDto.from(instructor.get());
            }else {
                throw new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_FOUND );
            }
        } catch (Exception e) {
            throw new InstructorException(InstructorResponseStatus.UNKNOWN_ERROR );
        }
    }


    //Todo 어떻게 만들어야 할지 잘 모르곘어서 놔둠
    //Todo User랑 instructor 랑 같이 수정할지 user 랑 instructor 따로 수정할지 잘 모르곘음 .
    // 강사 정보 수정
    @Transactional
    public void setInfo(Long instructorIdx, InstructorRequestDto dto, User user) {
        Instructor instructor = instructorRepository.findInstructorWithUser(instructorIdx)
                .orElseThrow(() -> new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_FOUND));

        // 사용자가 해당 강사의 정보 수정 권한이 있는지 확인
        if (!instructor.getUser().getIdx().equals(user.getIdx())) {
            throw new InstructorException(InstructorResponseStatus.INVALID_INSTRUCTOR_ID);
        }
        //        instructor.updateInfo(dto.toEntity(instructor));
    }


    @Transactional
    public void setInfo2(Long instructorIdx, UpdateUserInstructorDto dto) {
        try {
            userService.setInstructorInfo(instructorIdx, dto);
        } catch (Exception e) {
            throw new InstructorException(InstructorResponseStatus.UNKNOWN_ERROR);
        }
    }


    // 강사 리스트 조회
    public List<InstructorResponseDto> instructor_list() {
        try {
            List<Instructor> instructors = instructorRepository.findAll();
            return instructors.stream()
                    .map(InstructorResponseDto::from)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InstructorException(InstructorResponseStatus.UNKNOWN_ERROR );
        }
    }



    public List<InstructorResponseDto> instructor_list2() {
        try {
            List<User> users = userService.findUsersByRole("instruct");

            return users.stream()
                    .map(user -> {
                        Instructor instructor = (Instructor) instructorRepository.findByUser(user).orElse(null);
                        return InstructorResponseDto.from(instructor != null ? instructor : new Instructor(user, "기본 기록", "기본 포트폴리오"));
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new InstructorException(InstructorResponseStatus.UNKNOWN_ERROR);
        }
    }

}








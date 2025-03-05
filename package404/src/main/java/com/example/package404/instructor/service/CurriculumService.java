package com.example.package404.instructor.service;


import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Curriculum;
import com.example.package404.instructor.model.dto.req.CurriculumRegisterDto;
import com.example.package404.instructor.model.dto.res.CurriculumResponseDto;
import com.example.package404.instructor.repository.CurriculumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CurriculumService {
    private final CurriculumRepository curriculumRepository;


    public List<Curriculum> getCurriculumBySubject(String subject) {
        return  curriculumRepository.findByCurriculumSubjectIgnoreCase(subject);
    }


    public void registerCurriculum(List<CurriculumRegisterDto> curriculumList, Course course) {
        curriculumList.forEach(curriculumDto -> {
            // curriculumRepository.save() 호출을 CurriculumService에서 처리
            curriculumRepository.save(curriculumDto.toEntity(course));
        });

    }
}

package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDetailPageResponse {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    private List<StudentDetailResponseDto> studentList;

    public static StudentDetailPageResponse from(Page<StudentDetail> studentPage) {
        return StudentDetailPageResponse.builder()
                .page(studentPage.getNumber())
                .size(studentPage.getSize())
                .totalElements(studentPage.getTotalElements())
                .totalPages(studentPage.getTotalPages())
                .hasNext(studentPage.hasNext())
                .hasPrevious(studentPage.hasPrevious())
                .studentList(studentPage.stream().map(StudentDetailResponseDto::from).collect(Collectors.toList()))
                .build();
    }
}

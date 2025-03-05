package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDetailResponseDto {
    private Long idx;
    private String address;
    private Boolean testStatus;
    private Integer perception;
    private Integer attendance;
    private Integer leaveEarly;
    private Integer outing;
    private Integer vacationLeft;

    public static StudentDetailResponseDto from(StudentDetail studentDetail) {
        return StudentDetailResponseDto.builder()
                .idx(studentDetail.getIdx())
                .address(studentDetail.getAddress())
                .testStatus(studentDetail.getTestStatus())
                .perception(studentDetail.getPerception())
                .attendance(studentDetail.getAttendance())
                .leaveEarly(studentDetail.getLeaveEarly())
                .outing(studentDetail.getOuting())
                .vacationLeft(studentDetail.getVacationLeft())
                .build();
    }
}

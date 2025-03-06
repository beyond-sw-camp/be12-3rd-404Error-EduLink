package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDetailResponseDto {
    @Schema(description = "학생 고유 번호")
    private Long idx;
    @Schema(description = "학생 주소")
    private String address;
    @Schema(description = "학생 시험 상태")
    private Boolean testStatus;
    @Schema(description = "학생 지각 일수")
    private Integer perception;
    @Schema(description = "학생 출석 일수")
    private Integer attendance;
    @Schema(description = "학생 조퇴 일수")
    private Integer leaveEarly;
    @Schema(description = "학생 외출 일수")
    private Integer outing;
    @Schema(description = "학생 잔여 휴가 일수")
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

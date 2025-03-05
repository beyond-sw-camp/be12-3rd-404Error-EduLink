package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDetailUpdateDto {
    private Long idx;
    private String address;
    private Boolean testStatus;
    private Integer perception;
    private Integer attendance;
    private Integer leaveEarly;
    private Integer outing;
    private Integer vacationLeft;

    public static StudentDetail toEntity(StudentDetail studentDetail, String action) {
        if (action.equals("testStatus")) {
            return StudentDetail.builder()
                    .idx(studentDetail.getIdx())
                    .address(studentDetail.getAddress())
                    .testStatus(studentDetail.getTestStatus())
                    .perception(studentDetail.getPerception())
                    .attendance(studentDetail.getAttendance())
                    .leaveEarly(studentDetail.getLeaveEarly())
                    .outing(studentDetail.getOuting())
                    .vacationLeft(studentDetail.getVacationLeft())
                    .user(studentDetail.getUser())
                    .build();
        } else if (action.equals("perception")) {
            return StudentDetail.builder()
                    .idx(studentDetail.getIdx())
                    .address(studentDetail.getAddress())
                    .testStatus(studentDetail.getTestStatus())
                    .perception(studentDetail.getPerception()+1)
                    .attendance(studentDetail.getAttendance())
                    .leaveEarly(studentDetail.getLeaveEarly())
                    .outing(studentDetail.getOuting())
                    .vacationLeft(studentDetail.getVacationLeft())
                    .user(studentDetail.getUser())
                    .build();
        } else if (action.equals("attendance")) {
            return StudentDetail.builder()
                    .idx(studentDetail.getIdx())
                    .address(studentDetail.getAddress())
                    .testStatus(studentDetail.getTestStatus())
                    .perception(studentDetail.getPerception())
                    .attendance(studentDetail.getAttendance()+1)
                    .leaveEarly(studentDetail.getLeaveEarly())
                    .outing(studentDetail.getOuting())
                    .vacationLeft(studentDetail.getVacationLeft())
                    .user(studentDetail.getUser())
                    .build();
        } else if (action.equals("leaveEarly")) {
            return StudentDetail.builder()
                    .idx(studentDetail.getIdx())
                    .address(studentDetail.getAddress())
                    .testStatus(studentDetail.getTestStatus())
                    .perception(studentDetail.getPerception())
                    .attendance(studentDetail.getAttendance())
                    .leaveEarly(studentDetail.getLeaveEarly()+1)
                    .outing(studentDetail.getOuting())
                    .vacationLeft(studentDetail.getVacationLeft())
                    .user(studentDetail.getUser())
                    .build();
        } else if (action.equals("outing")) {
            return StudentDetail.builder()
                    .idx(studentDetail.getIdx())
                    .address(studentDetail.getAddress())
                    .testStatus(studentDetail.getTestStatus())
                    .perception(studentDetail.getPerception())
                    .attendance(studentDetail.getAttendance())
                    .leaveEarly(studentDetail.getLeaveEarly())
                    .outing(studentDetail.getOuting()+1)
                    .vacationLeft(studentDetail.getVacationLeft())
                    .user(studentDetail.getUser())
                    .build();
        } else if (action.equals("vacationLeft")) {
            return StudentDetail.builder()
                    .idx(studentDetail.getIdx())
                    .address(studentDetail.getAddress())
                    .testStatus(studentDetail.getTestStatus())
                    .perception(studentDetail.getPerception())
                    .attendance(studentDetail.getAttendance())
                    .leaveEarly(studentDetail.getLeaveEarly())
                    .outing(studentDetail.getOuting())
                    .vacationLeft(studentDetail.getVacationLeft()-1)
                    .user(studentDetail.getUser())
                    .build();
        }
        return studentDetail;
    }
}

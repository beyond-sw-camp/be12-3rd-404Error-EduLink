package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
import com.example.package404.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentDetailRegisterDto {
    private String address;
    private Boolean testStatus;
    private Integer perception;
    private Integer attendance;
    private Integer leaveEarly;
    private Integer outing;
    private Integer vacationLeft;

    public StudentDetail toEntity(User user) {
        return StudentDetail.builder()
                .address(address)
                .testStatus(testStatus)
                .perception(perception)
                .attendance(attendance)
                .leaveEarly(leaveEarly)
                .outing(outing)
                .vacationLeft(vacationLeft)
                .user(user)
                .build();
    }
}

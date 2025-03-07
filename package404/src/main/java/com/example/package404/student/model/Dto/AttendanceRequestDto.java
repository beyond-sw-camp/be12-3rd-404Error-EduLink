package com.example.package404.student.model.Dto;

import com.example.package404.student.model.Attendance_Logs;
import com.example.package404.student.model.StudentDetail;

import java.time.LocalDate;

public class AttendanceRequestDto {


    private String name;  // 사유 요약
    private LocalDate startDate;  // 시작 날짜
    private LocalDate endDate;  // 종료 날짜
    private String category;  // String 타입으로 그대로 사용
    private String description;
    private StudentDetail studentdetail; // 사유


    public Attendance_Logs from(AttendanceRequestDto attendanceRequestDto ,StudentDetail studentDetail) {
        return Attendance_Logs.builder()
                .name(attendanceRequestDto.name)
                .startDate(attendanceRequestDto.startDate)
                .endDate(attendanceRequestDto.endDate)
                .category(attendanceRequestDto.category)
                .description(attendanceRequestDto.description)
                .studentdetail(studentDetail)
                .build();
    }

}

package com.example.package404.instructor.model.dto.res;

import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorResponseDto {

    private Long userIdx;  // user의 PK
    private String record;
    private String portfolio;
    private String email;  // 유저 정보 추가
    private String name;

    public static InstructorResponseDto from(Instructor instructor) {
        return InstructorResponseDto.builder()
                .userIdx(instructor != null ? instructor.getUserIdx() : null)
                .record(instructor != null ? instructor.getRecord() : "기록 없음")
                .portfolio(instructor != null ? instructor.getPortfolio() : "기록 없음")
                .email(instructor != null ? instructor.getUser().getEmail() : "")
                .name(instructor != null ? instructor.getUser().getName() : "")
                .build();
    }

    public static InstructorResponseDto from(Instructor instructor ,User user ) {

        return InstructorResponseDto.builder()
                .userIdx(user.getIdx())
                .record(instructor != null ? instructor.getRecord() : "기록 없음")  // 기본값 설정
                .portfolio(instructor != null ? instructor.getPortfolio() : "기록 없음")  // 기본값 설정
                .email(user.getEmail())
                .name(user.getName())
                .build();

    }


}
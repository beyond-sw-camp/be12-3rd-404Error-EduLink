package com.example.package404.instructor.model.dto.res;

import com.example.package404.instructor.model.Instructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorIdDto {
    private Long userIdx;

//     Instructor Entity를 받아서 필요한 idx만 반환
    public static Instructor from(Instructor instructor) {
        return Instructor.builder()
                .userIdx(instructor.getUserIdx())
                .build();
    }
}
package com.example.package404.instructor.model;

import com.example.package404.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Instructor {
    @Id
    private Long userIdx; // user의 PK를 Instructor의 PK로 사용
    private String record;
    private String portfolio;

    @OneToOne
    @JoinColumn(name = "user_idx")
    private User user;


    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private List<Course> course;

    public Instructor(User user, String record, String portfolio) {
        this.user = user;
        this.record = record;
        this.portfolio = portfolio;
    }
}

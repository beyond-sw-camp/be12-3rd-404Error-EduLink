package com.example.package404.manager.model;

import com.example.package404.instructor.model.Course;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name="course_idx")
    private Course course;
}

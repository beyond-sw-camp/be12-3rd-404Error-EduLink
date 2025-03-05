package com.example.package404.board.model;

import com.example.package404.comment.model.Comment;
import com.example.package404.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    // 0 = 공지 게시판 / 1 = 일반 게시판 /
    private int boardType;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

}




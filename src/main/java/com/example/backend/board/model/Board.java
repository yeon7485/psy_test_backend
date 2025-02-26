package com.example.backend.board.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;
    private String contents;
    private String writer;

    private int commentCount;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public void addCommentsCount() {
        this.commentCount += 1;
    }
    public void subCommentsCount() {
        this.commentCount -= 1;
    }
}

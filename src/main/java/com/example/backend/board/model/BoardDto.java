package com.example.backend.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {
    @Getter
    public static class BoardCreate {
        private String title;
        private String contents;
        private String writer;

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .contents(contents)
                    .writer(writer)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BoardRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;
        private List<CommentRes> comments = new ArrayList<>();

        public static BoardRes from(Board board) {
            return BoardRes.builder()
                    .idx(board.getIdx())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .writer(board.getWriter())
                    .comments(board.getComments().stream().map(CommentRes::from).collect(Collectors.toList()))
                    .build();
        }
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BoardPageRes {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;
        private boolean hasPrevious;

        private List<BoardRes> boards;

        public static BoardPageRes from(Page<Board> boardPage) {
            return BoardPageRes.builder()
                    .page(boardPage.getNumber())
                    .size(boardPage.getSize())
                    .totalElements(boardPage.getTotalElements())
                    .totalPages(boardPage.getTotalPages())
                    .hasNext(boardPage.hasNext())
                    .hasPrevious(boardPage.hasPrevious())
                    .boards(boardPage.stream().map(BoardRes::from).collect(Collectors.toList()))
                    .build();
        }
    }


    @Getter
    public static class CommentCreate {
        private String content;
        private String writer;
        private Board board;

        public Comment toEntity(Board board) {
            return Comment.builder()
                    .content(content)
                    .writer(writer)
                    .board(board)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class CommentRes {
        private Long idx;
        private String content;
        private String writer;

        public static CommentRes from(Comment comment) {
            return CommentRes.builder()
                    .idx(comment.getIdx())
                    .content(comment.getContent())
                    .writer(comment.getWriter())
                    .build();
        }
    }

}

package com.example.backend.board.model;

import lombok.Builder;
import lombok.Getter;

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

    @Getter @Builder
    public static class BoardRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;

        public static BoardRes from(Board board) {
            return BoardRes.builder()
                    .idx(board.getIdx())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .writer(board.getWriter())
                    .build();
        }
    }
}

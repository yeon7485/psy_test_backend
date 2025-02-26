package com.example.backend.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "제목", example = "제목 01")
        private String title;
        @Schema(description = "내용", example = "내용 01")
        private String contents;
        @Schema(description = "작성자", example = "작성자 01")
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
        @Schema(description = "게시글 번호", example = "1")
        private Long idx;
        @Schema(description = "제목", example = "제목 01")
        private String title;
        @Schema(description = "내용", example = "내용 01")
        private String contents;
        @Schema(description = "작성자", example = "작성자 01")
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
        @Schema(description = "페이지 번호", example = "0")
        private int page;
        @Schema(description = "한 페이지 당 데이터 개수", example = "10")
        private int size;
        @Schema(description = "전체 데이터 개수", example = "15")
        private long totalElements;
        @Schema(description = "전체 페이지 개수", example = "2")
        private int totalPages;
        @Schema(description = "다음 페이지 존재 여부", example = "true")
        private boolean hasNext;
        @Schema(description = "이전 페이지 존재 여부", example = "false")
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
        @Schema(description = "내용", example = "내용 01")
        private String content;
        @Schema(description = "작성자", example = "작성자 01")
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
        @Schema(description = "댓글 번호", example = "1")
        private Long idx;
        @Schema(description = "내용", example = "내용 01")
        private String content;
        @Schema(description = "작성자", example = "작성자 01")
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

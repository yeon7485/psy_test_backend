package com.example.backend.board;

import com.example.backend.board.model.BoardDto;
import com.example.backend.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "게시글 작성", description = "게시글을 작성하는 기능입니다.")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createBoard(@RequestBody BoardDto.BoardCreate dto) {
        boardService.create(dto);

        return ResponseEntity.ok(new BaseResponse<>(true, "게시글 등록 성공", "success"));
    }

    @Operation(summary = "게시글 전체 조회", description = "게시글의 전체 목록을 조회하는 기능입니다.")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<BoardDto.BoardPageRes>> getBoards(int page, int size) {
        BoardDto.BoardPageRes boards = boardService.getBoards(page, size);

        return ResponseEntity.ok(new BaseResponse<>(true, "게시판 목록 조회 성공", boards));
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글의 idx 값으로 게시글을 조회하는 기능입니다.")
    @GetMapping("/read/{idx}")
    public ResponseEntity<BaseResponse<BoardDto.BoardRes>> getBoard(@PathVariable Long idx) {
        BoardDto.BoardRes board = boardService.getBoard(idx);

        return ResponseEntity.ok(new BaseResponse<>(true, "게시판 상세 조회 성공", board));
    }

    @Operation(summary = "댓글 작성", description = "게시글의 idx 값으로 게시글의 댓글을 작성하는 기능입니다.")
    @PostMapping("/comment/{boardIdx}")
    public ResponseEntity<BaseResponse<BoardDto.CommentRes>> createComment(@RequestBody BoardDto.CommentCreate dto, @PathVariable Long boardIdx) {
        BoardDto.CommentRes comment = boardService.addComment(boardIdx, dto);

        return ResponseEntity.ok(new BaseResponse<>(true, "댓글 작성 성공", comment));
    }

}

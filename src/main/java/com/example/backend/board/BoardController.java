package com.example.backend.board;

import com.example.backend.board.model.Board;
import com.example.backend.board.model.BoardDto;
import com.example.backend.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createBoard(@RequestBody BoardDto.BoardCreate dto) {
        boardService.create(dto);

        return ResponseEntity.ok(new BaseResponse<>(true, "게시글 등록 성공", "success"));
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<BoardDto.BoardPageRes>> getBoards(int page, int size) {
        BoardDto.BoardPageRes boards = boardService.getBoards(page, size);

        return ResponseEntity.ok(new BaseResponse<>(true, "게시판 목록 조회 성공", boards));
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity<BaseResponse<BoardDto.BoardRes>> getBoard(@PathVariable Long idx) {
        BoardDto.BoardRes board = boardService.getBoard(idx);

        return ResponseEntity.ok(new BaseResponse<>(true, "게시판 상세 조회 성공", board));
    }


}

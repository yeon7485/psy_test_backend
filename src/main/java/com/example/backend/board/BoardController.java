package com.example.backend.board;

import com.example.backend.board.model.Board;
import com.example.backend.board.model.BoardDto;
import com.example.backend.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

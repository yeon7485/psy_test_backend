package com.example.backend.board;

import com.example.backend.board.model.Board;
import com.example.backend.board.model.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public void create(BoardDto.BoardCreate dto) {
        Board board = boardRepository.save(dto.toEntity());


    }
}

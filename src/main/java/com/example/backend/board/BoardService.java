package com.example.backend.board;

import com.example.backend.board.model.Board;
import com.example.backend.board.model.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public void create(BoardDto.BoardCreate dto) {
        Board board = boardRepository.save(dto.toEntity());
    }

    public BoardDto.BoardPageRes getBoards(int page, int size) {
        Page<Board> result = boardRepository.findAll(PageRequest.of(page, size));

        return BoardDto.BoardPageRes.from(result);
    }


    public BoardDto.BoardRes getBoard(Long idx) {
        Board board = boardRepository.findById(idx).orElseThrow();

        return BoardDto.BoardRes.from(board);
    }
}

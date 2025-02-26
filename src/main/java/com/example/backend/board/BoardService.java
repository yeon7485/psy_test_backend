package com.example.backend.board;

import com.example.backend.board.model.Board;
import com.example.backend.board.model.BoardDto;
import com.example.backend.board.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void create(BoardDto.BoardCreate dto) {
        Board board = boardRepository.save(dto.toEntity());
    }

    public BoardDto.BoardPageRes getBoards(int page, int size) {
        Page<Board> result = boardRepository.findAllByOrderByIdxDesc(PageRequest.of(page, size));

        return BoardDto.BoardPageRes.from(result);
    }


    public BoardDto.BoardRes getBoard(Long idx) {
        Board board = boardRepository.findById(idx).orElseThrow();

        return BoardDto.BoardRes.from(board);
    }

    public BoardDto.CommentRes addComment(Long boardIdx, BoardDto.CommentCreate dto) {
        Board board = boardRepository.findById(boardIdx).orElseThrow();
        Comment comment = commentRepository.save(dto.toEntity(board));
        board.addCommentsCount();
        boardRepository.save(board);

        return BoardDto.CommentRes.from(comment);
    }
}

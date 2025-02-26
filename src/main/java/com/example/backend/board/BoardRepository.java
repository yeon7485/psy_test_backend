package com.example.backend.board;

import com.example.backend.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByOrderByIdxDesc(PageRequest pageRequest);
}

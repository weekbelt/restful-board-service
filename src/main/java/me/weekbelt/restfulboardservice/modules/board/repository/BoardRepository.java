package me.weekbelt.restfulboardservice.modules.board.repository;

import me.weekbelt.restfulboardservice.modules.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

package me.weekbelt.restfulboardservice.modules.board.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.restfulboardservice.modules.board.Board;
import me.weekbelt.restfulboardservice.modules.board.BoardType;
import me.weekbelt.restfulboardservice.modules.board.form.CreateBoardForm;
import me.weekbelt.restfulboardservice.modules.board.form.ReadBoardForm;
import me.weekbelt.restfulboardservice.modules.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Long createBoard(CreateBoardForm createBoardForm) {
        Board board = Board.builder()
                .title(createBoardForm.getTitle())
                .content(createBoardForm.getContent())
                .viewCount(0)
                .boardType(BoardType.valueOf(createBoardForm.getBoardType()))
                .build();

        boardRepository.save(board);
        return board.getId();
    }

    public ReadBoardForm findBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다. id=" + boardId));

        return ReadBoardForm.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardType(board.getBoardType().name())
                .build();

    }
}

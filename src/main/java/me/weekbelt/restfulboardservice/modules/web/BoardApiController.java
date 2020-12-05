package me.weekbelt.restfulboardservice.modules.web;

import lombok.RequiredArgsConstructor;
import me.weekbelt.restfulboardservice.modules.board.CreateBoardFormValidator;
import me.weekbelt.restfulboardservice.modules.board.form.CreateBoardForm;
import me.weekbelt.restfulboardservice.modules.board.form.ReadBoardForm;
import me.weekbelt.restfulboardservice.modules.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final CreateBoardFormValidator createBoardFormValidator;
    private final BoardService boardService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(createBoardFormValidator);
    }

    @PostMapping("/v1/movies")
    public ResponseEntity<?> createBoard(@RequestBody @Valid CreateBoardForm createBoardForm,
                                         Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Long boardId = boardService.createBoard(createBoardForm);
        ReadBoardForm readBoardForm = boardService.findBoardById(boardId);
        return ResponseEntity.ok(readBoardForm);

    }
}

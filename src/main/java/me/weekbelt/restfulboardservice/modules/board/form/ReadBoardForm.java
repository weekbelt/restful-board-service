package me.weekbelt.restfulboardservice.modules.board.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class ReadBoardForm {

    private final Long boardId;

    private final String title;

    private final String content;

    private final String boardType;
}

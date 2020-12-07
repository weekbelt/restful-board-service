package me.weekbelt.restfulboardservice.modules.board.form;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class CreateBoardForm {

    @NotBlank
    @Length(min = 2, max = 30)
    private final String title;

    @NotBlank
    @Length(min = 10, message = "10자 이상 입력해 주세요.")
    private final String content;

    private final String boardType;
}

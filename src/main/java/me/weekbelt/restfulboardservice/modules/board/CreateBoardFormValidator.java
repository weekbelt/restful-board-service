package me.weekbelt.restfulboardservice.modules.board;

import lombok.RequiredArgsConstructor;
import me.weekbelt.restfulboardservice.modules.board.form.CreateBoardForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CreateBoardFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateBoardForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateBoardForm createBoardForm = (CreateBoardForm) target;
        if (!createBoardForm.getBoardType().equals(BoardType.FREE.name()) &&
                !createBoardForm.getBoardType().equals(BoardType.NOTICE.name()) &&
                !createBoardForm.getBoardType().equals(BoardType.PROMOTION.name()) &&
                !createBoardForm.getBoardType().equals(BoardType.QUESTION.name())) {
            errors.rejectValue("boardType", "wrong.value", "게시글의 유형이 잘못되었습니다.");
        }
    }
}

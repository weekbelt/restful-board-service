package me.weekbelt.restfulboardservice.modules.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.weekbelt.restfulboardservice.modules.board.form.CreateBoardForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BoardApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Board 생성 API")
    void createBoard() throws Exception {
        // given
        String requestUri = "/api/v1/boards";

        CreateBoardForm createBoardForm = CreateBoardForm.builder()
                .title("test")
                .content("test content")
                .boardType("NOTICE")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(post(requestUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(createBoardForm))
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("boardId").exists())
                .andExpect(jsonPath("title").value(createBoardForm.getTitle()))
                .andExpect(jsonPath("content").value(createBoardForm.getContent()))
                .andExpect(jsonPath("boardType").value(createBoardForm.getBoardType()))
        ;
    }

    @Test
    @DisplayName("Board 생성 API - 잘못된 요청")
    void createBoard_BadRequest() throws Exception {
        // given
        String requestUri = "/api/v1/boards";

        CreateBoardForm createBoardForm = CreateBoardForm.builder()
                .title("")
                .content("test content")
                .boardType("NOTICE")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(post(requestUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(createBoardForm))
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].objectName").exists())
                .andExpect(jsonPath("$[0].field").exists())
                .andExpect(jsonPath("$[0].code").exists())
                .andExpect(jsonPath("$[0].defaultMessage").exists())
                .andExpect(jsonPath("$[0].rejectValue").exists())
        ;
    }
}
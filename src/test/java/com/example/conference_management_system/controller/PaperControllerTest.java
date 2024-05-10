package com.example.conference_management_system.controller;

import com.example.conference_management_system.dto.SubmittedPaper;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.service.paperService.PaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PaperController.class)
public class PaperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaperService paperService;

    private Paper paper;

    @BeforeEach
    void setUp() {
        paper = new Paper();
        paper.setPaperId(1);
        paper.setTitle("test title");
        paper.setPaperAbstract("test paper abstract");
    }

    @Test
    void submitPaper_ShouldReturnSubmittedPaper() throws Exception {
        SubmittedPaper submittedPaper = new SubmittedPaper();
        submittedPaper.setTitle("test title");
        submittedPaper.setPaperAbstract("test paper abstract");

        given(paperService.submitPaper(any(SubmittedPaper.class))).willReturn(paper);

        mockMvc.perform(post("/api/papers/submitPaper")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"" + submittedPaper.getTitle() + "\", " +
                                "\"abstractText\":\"" + submittedPaper.getPaperAbstract() + "\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paperId").value(paper.getPaperId()))
                .andExpect(jsonPath("$.title").value(paper.getTitle()))
                .andExpect(jsonPath("$.paperAbstract").value(paper.getPaperAbstract()));
    }
}

package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.utils.PaperStatus;
import com.example.conference_management_system.utils.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PaperRepositoryTest {

    @Autowired
    private PaperRepository paperRepository;
    private User author;
    private Conference conference;
    private Paper paper;

    @BeforeEach
    public void setup(){
        author = User.builder().userRole(UserRole.Author).password("password")
                .username("username").email("test@gmail.com").build();

        conference = Conference.builder().theme("theme").title("title").date(null)
                .location("location").build();

        paper = Paper.builder().conference(conference).status(PaperStatus.Pending)
                .paperUrl("testUrl.com").keywords("keywords").senderUser(author)
                .build();
    }

    @Test
    public void paperRepository_saveAll_returnSavedPaper(){
        // act
        Paper result = paperRepository.save(paper);

        // assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPaperId()).isGreaterThan(0);
    }

    @Test
    public void paperRepository_findPaperByPaperId_returnSavedPaper(){
        // act
        Optional<Paper> result = paperRepository
                .findPaperByPaperId(paper.getPaperId());

        // assert
        Assertions.assertThat(result).isNotNull();

    }

}
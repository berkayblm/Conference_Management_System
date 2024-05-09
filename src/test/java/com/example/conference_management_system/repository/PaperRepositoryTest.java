package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.utils.PaperStatus;
import com.example.conference_management_system.utils.UserRole;
import org.assertj.core.api.Assertions;
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

    @Test
    public void paperRepository_saveAll_returnSavedPaper(){

        User author = new User();
        author.setUserRole(UserRole.Author);
        author.setPassword("password");
        author.setUsername("username");
        author.setEmail("email");

        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        Paper paper = new Paper();
        paper.setConference(conference);
        paper.setStatus(PaperStatus.Pending);
        paper.setPaperUrl("url");
        paper.setKeywords("keywords");
        paper.setSenderUser(author);

        // act
        Paper result = paperRepository.save(paper);

        // assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPaperId()).isGreaterThan(0);
    }

    @Test
    public void paperRepository_findPaperByPaperId_returnSavedPaper(){

        User author = new User();
        author.setUserRole(UserRole.Author);
        author.setPassword("password");
        author.setUsername("username");
        author.setEmail("email");

        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        Paper paper = new Paper();
        paper.setConference(conference);
        paper.setStatus(PaperStatus.Pending);
        paper.setPaperUrl("url");
        paper.setKeywords("keywords");
        paper.setSenderUser(author);

        // act
        Optional<Paper> result = paperRepository
                .findPaperByPaperId(paper.getPaperId());

        // assert
        Assertions.assertThat(result).isNotNull();

    }

}
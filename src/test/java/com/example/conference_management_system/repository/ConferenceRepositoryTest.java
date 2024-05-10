package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Conference;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Test
    public void conferenceRepository_saveAll_returnSavedConference(){

        // arrange
        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        // act
        Conference result = conferenceRepository.save(conference);

        // assert
        Assertions.assertThat(result).isNotNull();
        assertEquals(result, conference);
        Assertions.assertThat(result.getConferenceId()).isGreaterThan(0);

    }

    @Test
    public void conferenceRepository_getConferenceByConferenceId_returnConferenceNotNull(){
        // arrange
        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        // act
        Optional<Conference> result = conferenceRepository
                .getConferenceByConferenceId(conference.getConferenceId());

        // assert
        Assertions.assertThat(result).isNotNull();

    }

    @Test
    public void conferenceRepository_updateConference_returnConferenceNotNull(){
        // arrange
        Conference conference = Conference.builder()
                .conferenceId(1)
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        // act
        Optional<Conference> result = conferenceRepository
                .getConferenceByConferenceId(conference.getConferenceId());

        //todo: fix the problem below
        // conference id vermediğimizde 0 olarak assign ediyor
        // bu durumda da alttaki kod hata veriyor id si 0 olan conference olmadığı için
        Conference savedConference = result.get();
        savedConference.setTitle("updated title");
        savedConference.setTheme("updated theme");

        Conference updatedResult = conferenceRepository.save(savedConference);

        // assert
        Assertions.assertThat(updatedResult).isNotNull();
        Assertions.assertThat(updatedResult.getConferenceId()).isGreaterThan(0);
    }

    @Test
    public void conferenceRepository_getAllConference_returnConferenceListNotNull(){
        // arrange
        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        // act
        Optional<Conference> result = conferenceRepository
                .getConferenceByConferenceId(conference.getConferenceId());

        // assert
        Assertions.assertThat(result).isNotNull();

    }


}
package com.example.conference_management_system.service.conferenceService;

import com.example.conference_management_system.repository.ConferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ConferenceServiceTest {

    private ConferenceServiceImpl conferenceService;

    private ConferenceRepository conferenceRepository;

    @BeforeEach
    void setUp() {
        conferenceRepository = Mockito.mock(ConferenceRepository.class);
        conferenceService = new ConferenceServiceImpl(conferenceRepository);

    }

    @Test
    void getAllConferences() {
//        Mockito.when(conferenceRepository.save(conference)).thenReturn(conference);
    }
}
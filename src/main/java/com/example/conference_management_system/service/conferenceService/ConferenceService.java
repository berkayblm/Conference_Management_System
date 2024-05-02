package com.example.conference_management_system.service.conferenceService;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }
}

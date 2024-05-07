package com.example.conference_management_system.service.conferenceService;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.repository.ConferenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ConferenceServiceImpl implements ConferenceService{

    @Autowired
    private ConferenceRepository conferenceRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository){
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }
}

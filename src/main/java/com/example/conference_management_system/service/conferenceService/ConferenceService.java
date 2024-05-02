package com.example.conference_management_system.service.conferenceService;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ConferenceService {

     List<Conference> getAllConferences();

}

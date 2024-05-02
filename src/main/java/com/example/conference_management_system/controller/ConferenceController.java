package com.example.conference_management_system.controller;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.service.conferenceService.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public List<Conference> getAllConferences() {
        return conferenceService.getAllConferences();
    }
}

package com.example.conference_management_system.service.paperService;

import com.example.conference_management_system.dto.SubmittedPaper;
import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.repository.ConferenceRepository;
import com.example.conference_management_system.repository.PaperRepository;
import com.example.conference_management_system.repository.UserRepository;
import com.example.conference_management_system.utils.PaperStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConferenceRepository conferenceRepository;
    @Autowired
    private PaperRepository paperRepository;


    @Override
    public String submitPaper(SubmittedPaper submittedPaper)  {

        Optional<User> user =
                userRepository
                        .getUserByUserId(
                                submittedPaper
                                        .getSenderUserId());

        Optional<Conference> conference = conferenceRepository
                .getConferenceByConferenceId(
                        submittedPaper
                                .getConferenceId());

        PaperStatus status = PaperStatus.Pending;

        Paper paper = new Paper();
        paper.setPaperUrl(submittedPaper.getPaperUrl());
        paper.setTitle(submittedPaper.getTitle());
        paper.setPaperAbstract(submittedPaper.getPaperAbstract());
        paper.setKeywords(submittedPaper.getKeywords());
        paper.setStatus(status);
        conference.ifPresent(paper::setConference);
        user.ifPresent(paper::setSenderUser);

        paperRepository.save(paper);

        return "OK";



    }
}

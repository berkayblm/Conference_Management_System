package com.example.conference_management_system.controller;

import com.example.conference_management_system.dto.SubmittedPaper;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.service.paperService.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    @Autowired
    PaperService paperService;

    @PostMapping("/submitPaper")
    public Paper submitPaper(@RequestBody SubmittedPaper submittedPaper) {

        Paper result = paperService.submitPaper(submittedPaper);

        return result;

    }
}

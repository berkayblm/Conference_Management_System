package com.example.conference_management_system.controller;

import com.example.conference_management_system.dto.SubmittedPaper;
import com.example.conference_management_system.service.PaperService.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    @Autowired
    PaperService paperService;

    @PostMapping("/submitPaper")
    public String submitPaper(@RequestBody SubmittedPaper submittedPaper) {

        String result = paperService.submitPaper(submittedPaper);

        return result;

    }
}

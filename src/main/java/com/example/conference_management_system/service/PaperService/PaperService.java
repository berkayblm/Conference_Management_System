package com.example.conference_management_system.service.PaperService;

import com.example.conference_management_system.dto.SubmittedPaper;
import org.springframework.stereotype.Service;

@Service
public interface PaperService {

     String submitPaper(SubmittedPaper submittedPaper);
}

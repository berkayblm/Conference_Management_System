package com.example.conference_management_system.service.paperService;

import com.example.conference_management_system.dto.SubmittedPaper;
import org.springframework.stereotype.Service;
import com.example.conference_management_system.entity.Paper;

@Service
public interface PaperService {

     Paper submitPaper(SubmittedPaper submittedPaper);


}

package com.example.conference_management_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SubmittedPaper {

    private String title;
    private String paperAbstract;
    private String keywords;
    private String paperUrl;
    private int senderUserId;
    private int conferenceId;
}

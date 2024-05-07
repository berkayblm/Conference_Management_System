package com.example.conference_management_system.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReviewDTO {


    private int reviewId;
    private int paperId;
    private String status;
    private String rating;
    private String comment;


}
package com.example.conference_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REVIEW")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "paper_id")
    private Paper paper;

    @Column(name = "rating")
    private float rating;

    @Column(name = "comment")
    private String comment;

    // Getters and setters
}
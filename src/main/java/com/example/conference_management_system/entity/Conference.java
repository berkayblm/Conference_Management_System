package com.example.conference_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "CONFERENCE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")
    private int conferenceId;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private Date date;

    @Column(name = "theme")
    private String theme;

    @Column(name = "location")
    private String location;


}
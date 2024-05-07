package com.example.conference_management_system.entity;
import com.example.conference_management_system.utils.PaperStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PAPER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id")
    private int paperId;

    @Column(name = "title")
    private String title;

    @Column(name = "abstract")
    private String paperAbstract;

    @Column(name = "keywords")
    private String keywords;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaperStatus status;

    @Column(name = "paper_url")
    private String paperUrl;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

    // Getters and setters
}
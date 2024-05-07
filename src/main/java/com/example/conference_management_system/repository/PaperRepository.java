package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer> {

    Optional<Paper> findPaperByPaperId(int paperId);
}

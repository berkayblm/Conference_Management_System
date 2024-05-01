package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

    Optional<Conference> getConferenceByConferenceId(int conferenceId);
}

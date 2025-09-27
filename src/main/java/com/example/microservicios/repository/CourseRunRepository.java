package com.example.microservicios.repository;

import com.example.microservicios.model.CourseRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRunRepository extends JpaRepository<CourseRun, Long> {
    Optional<CourseRun> findById(Long id);
    List<CourseRun> findByTerm(String term);
    List<CourseRun> findByStatus(String status);
    List<CourseRun> findByTermAndStatus(String term, String status);
}
package com.example.microservicios.repository;

import com.example.microservicios.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByCourseRun_Id(Long courseRunId);
}
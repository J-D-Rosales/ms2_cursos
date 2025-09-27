package com.example.microservicios.service;

import com.example.microservicios.model.CourseRun;
import com.example.microservicios.repository.CourseRunRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRunService {

    private final CourseRunRepository repository;

    public CourseRunService(CourseRunRepository repository) {
        this.repository = repository;
    }

    public List<CourseRun> findAll() {
        return repository.findAll();
    }

    public Optional<CourseRun> findById(Long id) {
        return repository.findById(id);
    }

    public List<CourseRun> findByTerm(String term) {
        return repository.findByTerm(term);
    }

    public List<CourseRun> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<CourseRun> findByTermAndStatus(String term, String status) {
        return repository.findByTermAndStatus(term, status);
    }

    public CourseRun save(CourseRun courseRun) {
        return repository.save(courseRun);
    }

    public CourseRun update(Long id, CourseRun newRun) {
        return repository.findById(id).map(run -> {
            run.setTerm(newRun.getTerm());
            run.setStartDate(newRun.getStartDate());
            run.setEndDate(newRun.getEndDate());
            run.setModality(newRun.getModality());
            run.setCapacity(newRun.getCapacity());
            run.setEnrolled(newRun.getEnrolled());
            run.setStatus(newRun.getStatus());
            run.setCourse(newRun.getCourse());
            return repository.save(run);
        }).orElseThrow(() -> new RuntimeException("CourseRun not found"));
    }

    public void delete(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
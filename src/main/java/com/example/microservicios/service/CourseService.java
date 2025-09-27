package com.example.microservicios.service;

import com.example.microservicios.model.Course;
import com.example.microservicios.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Optional<Course> findById(Long id) {
        return repository.findById(id);
    }

    public Course save(Course course) {
        if (course.getCreatedAt() == null) {
            course.setCreatedAt(OffsetDateTime.now());
        }
        return repository.save(course);
    }

    public Course update(Long id, Course newCourse) {
        return repository.findById(id).map(course -> {
            course.setCode(newCourse.getCode());
            course.setTitle(newCourse.getTitle());
            course.setDescription(newCourse.getDescription());
            course.setCategory(newCourse.getCategory());
            course.setLevel(newCourse.getLevel());
            course.setHours(newCourse.getHours());
            course.setCredits(newCourse.getCredits());
            return repository.save(course);
        }).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
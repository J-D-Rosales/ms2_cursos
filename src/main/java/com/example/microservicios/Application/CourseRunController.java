package com.example.microservicios.Application;


import com.example.microservicios.model.Course;
import com.example.microservicios.model.CourseRun;
import com.example.microservicios.repository.CourseRepository;
import com.example.microservicios.repository.CourseRunRepository;
import com.example.microservicios.service.CourseRunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-runs")
public class CourseRunController {

    private final CourseRepository courseRepository;
    private final CourseRunService service;

    public CourseRunController(CourseRepository courseRunRepository, CourseRunService service) {
        this.courseRepository = courseRunRepository;
        this.service = service;
    }


    @GetMapping
    public List<CourseRun> getAll(
            @RequestParam(required = false) String term,
            @RequestParam(required = false) String status
    ) {
        if (term != null && status != null) {
            return service.findByTermAndStatus(term, status);
        } else if (term != null) {
            return service.findByTerm(term);
        } else if (status != null) {
            return service.findByStatus(status);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseRun> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CourseRun create(@RequestBody CourseRun run) {
        Course course = courseRepository.findById(run.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        run.setCourse(course);
        return service.save(run);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseRun> update(@PathVariable Long id, @RequestBody CourseRun courseRun) {
        if (courseRun.getCourse() != null && courseRun.getCourse().getId() != null) {
            try {
                Course course = courseRepository.findById(courseRun.getCourse().getId())
                        .orElseThrow(() -> new RuntimeException("Course not found"));
                courseRun.setCourse(course);
                return ResponseEntity.ok(service.update(id, courseRun));

            } catch (RuntimeException e) {

                return ResponseEntity.notFound().build();
            }
        } else {
            try {
                return ResponseEntity.ok(service.update(id, courseRun));
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Extra: availability
    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getAvailability(@PathVariable Long id) {
        return service.findById(id)
                .map(run -> ResponseEntity.ok(
                        new Availability(run.getStatus(), run.getCapacity(), run.getEnrolled())
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    record Availability(String status, int capacity, int enrolled) {}
}

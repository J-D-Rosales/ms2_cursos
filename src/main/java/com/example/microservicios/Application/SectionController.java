package com.example.microservicios.Application;

import com.example.microservicios.model.Section;
import com.example.microservicios.service.SectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SectionController {

    private final SectionService service;

    public SectionController(SectionService service) {
        this.service = service;
    }

    // Listar secciones de un run
    @GetMapping("/course-runs/{courseRunId}/sections")
    public List<Section> listByCourseRun(@PathVariable Long courseRunId) {
        return service.listByCourseRun(courseRunId);
    }

    // CRUD clásico de Section
    @GetMapping("/sections/{id}")
    public ResponseEntity<Section> get(@PathVariable Long id) {
        try { return ResponseEntity.ok(service.get(id)); }
        catch (RuntimeException e) {return ResponseEntity.notFound().build();}
    }

    @PostMapping("/course-runs/{courseRunId}/sections")
    public ResponseEntity<Section> create(@PathVariable Long courseRunId, @RequestBody Section s) {
        return ResponseEntity.ok(service.create(courseRunId, s));
    }

    @PutMapping("/sections/{id}")
    public ResponseEntity<Section> update(@PathVariable Long id, @RequestBody Section s) {
        try { return ResponseEntity.ok(service.update(id, s)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/sections/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
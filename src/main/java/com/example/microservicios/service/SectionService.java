package com.example.microservicios.service;

import com.example.microservicios.model.CourseRun;
import com.example.microservicios.model.Section;
import com.example.microservicios.repository.CourseRunRepository;
import com.example.microservicios.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseRunRepository courseRunRepository;

    public SectionService(SectionRepository sectionRepository, CourseRunRepository courseRunRepository) {
        this.sectionRepository = sectionRepository;
        this.courseRunRepository = courseRunRepository;
    }

    public List<Section> listByCourseRun(Long courseRunId) {
        return sectionRepository.findByCourseRun_Id(courseRunId);
    }

    public Section get(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
    }

    public Section create(Long courseRunId, Section s) {
        CourseRun run = courseRunRepository.findById(courseRunId)
                .orElseThrow(() -> new RuntimeException("CourseRun not found"));
        s.setCourseRun(run);
        return sectionRepository.save(s);
    }

    public Section update(Long id, Section newS) {
        Section s = get(id);
        if (newS.getCourseRun() != null && newS.getCourseRun().getId() != null) {
            CourseRun run = courseRunRepository.findById(newS.getCourseRun().getId())
                    .orElseThrow(() -> new RuntimeException("CourseRun not found"));
            s.setCourseRun(run);
        }
        s.setSectionCode(newS.getSectionCode());
        s.setInstructorUid(newS.getInstructorUid());
        s.setRoom(newS.getRoom());
        s.setSchedule(newS.getSchedule());
        return sectionRepository.save(s);
    }

    public void delete(Long id) {
        sectionRepository.deleteById(id);
    }
}
